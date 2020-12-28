package com.developerartemmotuznyi.sdhtest.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerartemmotuznyi.sdhtest.core.model.ActionResult
import com.developerartemmotuznyi.sdhtest.core.model.handle
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

abstract class BaseViewModel : ViewModel() {

    private val tasksProgress = AtomicInteger(0)
    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error


    fun showProgress(show: Boolean) {
        val value = if (show) {
            tasksProgress.incrementAndGet()
        } else {
            val taskInProgress = tasksProgress.get()
            if (taskInProgress > 0) tasksProgress.decrementAndGet() else taskInProgress
        }
        _progress.postValue(value != 0)
    }

    protected fun <T> launch(
            action: suspend () -> ActionResult<T>,
            onSuccess: suspend (T) -> Unit = {},
            errorHandler: (e: Throwable) -> Boolean = { false }
    ) {
        viewModelScope.launch {
            showProgress(true)
            action().handle({
                showProgress(false)
                onSuccess(it)
            }, {
                handleError(errorHandler, it)
                showProgress(false)
            })
        }
    }

    private fun handleError(errorHandler: (e: Throwable) -> Boolean, exception: Throwable) {
        if (!errorHandler(exception)) _error.postValue(exception)
    }

}