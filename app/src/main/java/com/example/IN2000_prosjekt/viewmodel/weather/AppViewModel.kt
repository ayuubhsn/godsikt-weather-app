package com.example.IN2000_prosjekt.viewmodel.weather

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.IN2000_prosjekt.model.weather.WeatherRepository
import com.example.IN2000_prosjekt.view.uistate.AppUiState
import com.example.IN2000_prosjekt.view.screens.WeatherCard
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

@Composable
fun WeatherScreenContent(
    mapViewModel: MapViewModel,
    appViewModel: AppViewModel
) {

    val appUiState by appViewModel.appUiState.collectAsState()
    val mapCoordinates by mapViewModel.mapClickedCoordinates.collectAsState()

    //val userLocation = mapViewModel.getLastUserLocation()

    Log.d("weatherscreen" , mapCoordinates.currentScreenLat.toString())
    Log.d("weatherscreen" , mapCoordinates.currentScreenLong.toString())

    // Hent informasjon basert på koordinater når de endres
    LaunchedEffect(mapCoordinates) {
        mapCoordinates.let {
            Log.d("weatherscreen", "getAll")
            appViewModel.getAll(mapCoordinates.currentScreenLat.toString(), mapCoordinates.currentScreenLong.toString())
        }
    }

    when (appUiState){
        is AppUiState.Loading ->{
            Text(text = "vent")
        }
        is AppUiState.Error ->{
            Text(text = "feil")
        }
        is AppUiState.Success ->{
            WeatherCard(
                (appUiState as AppUiState.Success).locationG,
                (appUiState as AppUiState.Success).metAlertG,
                mapCoordinates
            )
        }
    }
}

@Composable
fun WeatherCardInfo(
    mapViewModel: MapViewModel,
    appViewModel: AppViewModel
) {

    val appUiState by appViewModel.appUiState.collectAsState()
    val mapCoordinates by mapViewModel.mapClickedCoordinates.collectAsState()

    //val userLocation = mapViewModel.getLastUserLocation()

    Log.d("weatherscreen" , mapCoordinates.currentScreenLat.toString())
    Log.d("weatherscreen" , mapCoordinates.currentScreenLong.toString())

    // Get info based on coordinates when changed
    LaunchedEffect(mapCoordinates) {
        mapCoordinates.let {
            Log.d("weatherscreen", "getAll")
            appViewModel.getAll(mapCoordinates.currentScreenLat.toString(), mapCoordinates.currentScreenLong.toString())
        }
    }
    when (appUiState){
        is AppUiState.Loading ->{
            Text(text = "vent")
        }
        is AppUiState.Error ->{
            Text(text = "feil")
        }
        is AppUiState.Success ->{
            WeatherCard(
                (appUiState as AppUiState.Success).locationG,
                (appUiState as AppUiState.Success).metAlertG,
                mapCoordinates
            )
        }
    }
}


