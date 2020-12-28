package com.developerartemmotuznyi.networkconnectivitymanager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
internal class DefaultNetworkConnectivityProvider(
    context: Context
) : BaseNetworkConnectivityProvide(context) {

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onCapabilitiesChanged(network: Network, capabilities: NetworkCapabilities) {
            dispatchChange(
                NetworkConnectivityProvider.NetworkState.ConnectedState.Connected(
                    capabilities
                )
            )
        }

        override fun onLost(network: Network) {
            dispatchChange(NetworkConnectivityProvider.NetworkState.NotConnectedState)
        }
    }

    override fun getNetworkState(): NetworkConnectivityProvider.NetworkState {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return if (capabilities != null) {
            NetworkConnectivityProvider.NetworkState.ConnectedState.Connected(
                capabilities
            )
        } else {
            NetworkConnectivityProvider.NetworkState.NotConnectedState
        }
    }

    override fun subscribe() {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    override fun unsubscribe() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}