package com.developerartemmotuznyi.sdhtest.core.model

sealed class ActionResult<out R> {
    class Success<out T>(val data: T) : ActionResult<T>()
    class Error(val exception: Throwable) : ActionResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

