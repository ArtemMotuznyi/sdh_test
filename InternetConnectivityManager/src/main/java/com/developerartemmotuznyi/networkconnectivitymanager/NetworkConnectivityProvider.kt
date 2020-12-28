package com.developerartemmotuznyi.networkconnectivitymanager

import android.content.Context
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

interface NetworkConnectivityProvider {

    interface StateListener {
        fun onStateChanged(state: NetworkState)
    }

    companion object {
        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        fun createProvider(context: Context): NetworkConnectivityProvider {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                DefaultNetworkConnectivityProvider(
                    context
                )
            } else {
                CompatConnectivityProvider(context)
            }
        }

    }

    fun addStateListener(listener: StateListener)

    fun removeStateListener(listener: StateListener)

    fun getNetworkState(): NetworkState

    sealed class NetworkState {
        object NotConnectedState : NetworkState()

        sealed class ConnectedState(
            val hasInternet: Boolean
        ) : NetworkState() {

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            data class Connected(val capabilities: NetworkCapabilities) : ConnectedState(
                capabilities.hasCapability(NET_CAPABILITY_INTERNET)
            )

            @Suppress("DEPRECATION")
            data class ConnectedLegacy(val networkInfo: NetworkInfo) : ConnectedState(
                networkInfo.isConnectedOrConnecting
            )
        }
    }
}

