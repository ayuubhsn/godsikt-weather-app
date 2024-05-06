package com.example.IN2000_prosjekt.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.IN2000_prosjekt.data.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class AppViewModel :ViewModel() {
    private val repo = WeatherRepository()

    private val _appUistate: MutableStateFlow<AppUiState> = MutableStateFlow(AppUiState.Loading)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()

    fun getAll(longitude: String, latitude: String) {
        viewModelScope.launch {
            try {
                Log.d("getAll" , "getLocation")
                val locationFCFetchDeferred = viewModelScope.async(Dispatchers.IO) {
                    Log.d("getAll" , "getMetAlerts")
                    repo.getLocation( longitude,latitude)
                }
                val locationResult = locationFCFetchDeferred.await()

                Log.d("getAll" , "getMetAlerts")

                val metAlertFetchDeferred = viewModelScope.async(Dispatchers.IO) {
                    repo.getMetAlert(latitude,longitude)
                }
                val metAlertResult = metAlertFetchDeferred.await()
                Log.d("appviewmodel", "henter data")


                _appUistate.update {
                    AppUiState.Success(
                        locationG = locationResult,
                        metAlertG = metAlertResult,
                    )
                }
            } catch (e: IOException) {          //ved nettverksbrudd
                _appUistate.update {
                    AppUiState.Error
                }
            }
        }
    }
}

