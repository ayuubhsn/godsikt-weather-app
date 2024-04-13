package com.example.IN2000_prosjekt.model.weather


import androidx.lifecycle.ViewModel
import com.example.IN2000_prosjekt.ui.uistate.MapUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapViewModel : ViewModel() {

    private val _mapClickedCoordinatesUIState : MutableStateFlow<MapUIState.mapCoordinates> =MutableStateFlow(MapUIState.mapCoordinates())
    val mapClickedCoordinates: StateFlow<MapUIState.mapCoordinates> = _mapClickedCoordinatesUIState

    fun updateCoordinates(lat: Double, long: Double) {
        _mapClickedCoordinatesUIState.value = MapUIState.mapCoordinates(lat, long)
    }
}


