package com.developerartemmotuznyi.networkconnectivitymanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.net.ConnectivityManager.EXTRA_NETWORK_INFO
import android.net.NetworkInfo

internal class CompatConnectivityProvider(
    context: Context
) : BaseNetworkConnectivityProvide(context) {

    private val connectionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // on some devices ConnectivityManager.getActiveNetworkInfo() does not provide the correct network state
            // https://issuetracker.google.com/issues/37137911
            val networkInfo = connectivityManager.activeNetworkInfo
            val fallbackNetworkInfo: NetworkInfo? = intent?.getParcelableExtra(EXTRA_NETWORK_INFO)

            val state = when {
                networkInfo?.isConnectedOrConnecting == true -> NetworkConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy(
                    networkInfo
                )
                networkInfo != null && fallbackNetworkInfo != null && networkInfo.isConnectedOrConnecting != fallbackNetworkInfo.isConnectedOrConnecting ->
                    NetworkConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy(
                        fallbackNetworkInfo
                    )
                fallbackNetworkInfo?.isConnectedOrConnecting == true -> NetworkConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy(
                    fallbackNetworkInfo
                )
                else -> NetworkConnectivityProvider.NetworkState.NotConnectedState
            }
            dispatchChange(state)
        }

    }

    override fun getNetworkState(): NetworkConnectivityProvider.NetworkState {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return if (activeNetworkInfo != null) {
            NetworkConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy(
                activeNetworkInfo
            )
        } else {
            NetworkConnectivityProvider.NetworkState.NotConnectedState
        }
    }

    override fun subscribe() {
        context.registerReceiver(connectionReceiver, IntentFilter(CONNECTIVITY_ACTION))
    }

    override fun unsubscribe() {
        context.unregisterReceiver(connectionReceiver)
    }
}