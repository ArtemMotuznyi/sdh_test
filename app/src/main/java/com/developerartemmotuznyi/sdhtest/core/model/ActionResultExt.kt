package com.developerartemmotuznyi.sdhtest.core.model

inline fun <Data> ActionResult<Data>.handle(
    onSuccess: (Data) -> Unit,
    onError: (Throwable) -> Unit
) {
    when (this) {
        is ActionResult.Success -> onSuccess(data)
        is ActionResult.Error -> onError(this.exception)
    }
}

inline fun <Data, Result> ActionResult<Data>.handleWithResult(
    onSuccess: (Data) -> Result,
    onError: (Throwable) -> Result
): Result {
    return when (this) {
        is ActionResult.Success -> onSuccess(data)
        is ActionResult.Error -> onError(this.exception)
    }
}


suspend fun <Data, Result> ActionResult<Data>.transform(transform: suspend (Data) -> Result): ActionResult<Result> =
        try {
            when (this) {
                is ActionResult.Error -> this
                is ActionResult.Success -> ActionResult.Success(transform(this.data))
            }
        } catch (e: Exception) {
            ActionResult.Error(e)
        }

suspend fun <Data> ActionResult<Data>.doOnError(doOnError: suspend (Throwable) -> ActionResult<Data>): ActionResult<Data> = try {
    when (this) {
        is ActionResult.Error -> doOnError(this.exception)
        is ActionResult.Success -> this
    }
} catch (e: Exception) {
    ActionResult.Error(e)
}

suspend fun <T, R> ActionResult<T>.flatMap(transform: suspend (T) -> ActionResult<R>): ActionResult<R> = try {
    when (this) {
        is ActionResult.Error -> this
        is ActionResult.Success -> transform(this.data)
    }
} catch (e: Exception) {
    ActionResult.Error(e)
}


suspend fun <T, T1, R> ActionResult<T>.join(
    joinWith: suspend (T) -> ActionResult<T1>,
    join: suspend (T, T1) -> R
): ActionResult<R> {
    val mainData = when (this) {
        is ActionResult.Success -> data
        is ActionResult.Error -> return this
    }

    val joinWithData = when (val result = joinWith(mainData)) {
        is ActionResult.Success -> result.data
        is ActionResult.Error -> return result
    }

    return ActionResult.Success(join(mainData, joinWithData))
}