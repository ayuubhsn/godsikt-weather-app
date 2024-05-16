package com.example.IN2000_prosjekt.model.Internett

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class NetworkConnectionObserver(
    private val context: Context

):NetworkObserver {
    private val connectivityanager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            ?: throw IllegalStateException("Failed to get ConnectivityManager")

    override fun oberrver(): Flow<NetworkObserver.Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(NetworkObserver.Status.Tilgjengelig) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(NetworkObserver.Status.Utilgjengelig) }
                }
            }
            connectivityanager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityanager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()


    }


    fun isNetworkAvailable(): Boolean {
        val activeNetwork = connectivityanager.activeNetwork ?: return false
        val networkCapabilities = connectivityanager.getNetworkCapabilities(activeNetwork) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }
}


