package com.developerartemmotuznyi.sdhtest.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class ViewModelFragment<VB : ViewBinding, VM : ViewModel> : ViewBindingFragment<VB>() {

    abstract val viewModel: VM

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSubscription()
    }

    @CallSuper
    protected open fun initSubscription() = Unit
}