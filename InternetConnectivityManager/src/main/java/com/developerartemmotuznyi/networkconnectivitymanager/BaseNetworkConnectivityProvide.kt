package com.developerartemmotuznyi.networkconnectivitymanager

import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import java.util.concurrent.CopyOnWriteArrayList

internal abstract class BaseNetworkConnectivityProvide(context: Context) :
    NetworkConnectivityProvider {

    protected val context = context.applicationContext
    protected val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val handler = Handler(Looper.getMainLooper())

    private val listeners =
        CopyOnWriteArrayList<NetworkConnectivityProvider.StateListener>()


    override fun addStateListener(listener: NetworkConnectivityProvider.StateListener) {
        listeners.addIfAbsent(listener)
        listener.onStateChanged(getNetworkState())

        verifySubscription()
    }

    override fun removeStateListener(listener: NetworkConnectivityProvider.StateListener) {
        listeners.remove(listener)
        verifySubscription()
    }

    private fun verifySubscription() {
        if (listeners.isEmpty()) {
            unsubscribe()
        } else if (listeners.size == 1) {
            subscribe()
        }
    }

    protected fun dispatchChange(state: NetworkConnectivityProvider.NetworkState) {
        handler.post { listeners.forEach { it.onStateChanged(state) } }
    }


    protected abstract fun subscribe()
    protected abstract fun unsubscribe()


}