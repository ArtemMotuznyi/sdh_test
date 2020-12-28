package com.developerartemmotuznyi.sdhtest.presentation.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.CallSuper
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.developerartemmotuznyi.sdhtest.presentation.extensions.noInternetConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ViewModelFragment<VB : ViewBinding, VM : BaseViewModel> : ViewBindingFragment<VB>() {

    abstract val viewModel: VM

    protected var progress: ProgressBar? = null

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSubscription()
    }

    @CallSuper
    open fun initSubscription() {
        viewModel.progress.observe(viewLifecycleOwner) {
            showProgress(it ?: false)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            it?.let(::showErrorDialog)
        }
    }

    private fun showProgress(show: Boolean) {
        progress?.isVisible = show
    }

    private fun showErrorDialog(exception: Throwable) {
        if (exception is SocketTimeoutException || exception is UnknownHostException) {
            showNoInternetConnection()
        } else {
            showError(exception)
        }
    }

    @CallSuper
    open fun showNoInternetConnection() {
        noInternetConnection(requireContext())
    }

    @CallSuper
    open fun showError(exception: Throwable) {
        AlertDialog.Builder(requireContext())
            .setTitle("Ошибка")
            .setMessage(exception.message)
            .setPositiveButton("Ok", null)
            .show()
    }
}