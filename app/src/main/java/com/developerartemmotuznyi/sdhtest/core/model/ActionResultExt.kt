package com.developerartemmotuznyi.sdhtest.core.model

inline fun <R> ActionResult<R>.getOrDefault(default: () -> R): R {
    return when (this) {
        is ActionResult.Success -> data
        is ActionResult.Error -> default()
    }
}

suspend fun <R> ActionResult<R>.doOnSuccess(action: suspend (R) -> Unit): ActionResult<R> = apply {
    if (this is ActionResult.Success) {
        action(data)
    }
}

suspend fun <R> ActionResult<R>.doOnError(action: suspend () -> Unit): ActionResult<R> = apply {
    if (this is ActionResult.Error) {
        action()
    }
}


suspend fun <T, R> ActionResult<T>.transform(transform: suspend (T) -> R): ActionResult<R> =
    try {
        when (this) {
            is ActionResult.Error -> this
            is ActionResult.Success -> ActionResult.Success(transform(this.data))
        }
    } catch (e: Exception) {
        ActionResult.Error(e)
    }

suspend fun <T, R> ActionResult<T>.flatMap(transform: suspend (T) -> ActionResult<R>): ActionResult<R> =
    try {
        when (this) {
            is ActionResult.Error -> this
            is ActionResult.Success -> transform(this.data)
        }
    } catch (e: Exception) {
        ActionResult.Error(e)
    }


suspend fun <T, T1, R> ActionResult<T>.join(
    joinWith: ActionResult<T1>,
    join: suspend (T, T1) -> R
): ActionResult<R> {
    val mainData = when (this) {
        is ActionResult.Success -> data
        is ActionResult.Error -> return this
    }

    val joinWithData = when (joinWith) {
        is ActionResult.Success -> joinWith.data
        is ActionResult.Error -> return joinWith
    }

    return ActionResult.Success(join(mainData, joinWithData))
}

suspend fun <T, T1, R> ActionResult<T>.join(
    joinWith: suspend (T) -> ActionResult<T1>,
    join: suspend (T, T1) -> R
): ActionResult<R> {
    val mainData = when (this) {
        is ActionResult.Success -> data
        is ActionResult.Error -> return this
    }

    val joinWithData = when (val joinWithActionResult = joinWith(mainData)) {
        is ActionResult.Success -> joinWithActionResult.data
        is ActionResult.Error -> return joinWithActionResult
    }

    return ActionResult.Success(join(mainData, joinWithData))
}

suspend fun <T, T1, T2, R> ActionResult<T>.join(
    joinWith0: suspend (T) -> ActionResult<T1>,
    joinWith1: suspend (T) -> ActionResult<T2>,
    join: suspend (T, T1, T2) -> R
): ActionResult<R> {

    val mainData = when (this) {
        is ActionResult.Success -> data
        is ActionResult.Error -> return this
    }

    val joinWithData0 = when (val joinWithActionResult = joinWith0(mainData)) {
        is ActionResult.Success -> joinWithActionResult.data
        is ActionResult.Error -> return joinWithActionResult
    }

    val joinWithData1 = when (val joinWithActionResult = joinWith1(mainData)) {
        is ActionResult.Success -> joinWithActionResult.data
        is ActionResult.Error -> return joinWithActionResult
    }

    return ActionResult.Success(join(mainData, joinWithData0, joinWithData1))
}