package com.example.IN2000_prosjekt.model.Internett

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkConnectionObserver(
    private val context:Context

):NetworkObserver {
    private val connectivityanager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    override fun oberrver(): Flow<NetworkObserver.Status> {
        return callbackFlow {
            val callback = object :ConnectivityManager.NetworkCallback(){
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
            awaitClose{
                connectivityanager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()


    }

}




