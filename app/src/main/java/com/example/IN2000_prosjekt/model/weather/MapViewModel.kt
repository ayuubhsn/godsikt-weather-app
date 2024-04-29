package com.example.IN2000_prosjekt.model.weather


import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.IN2000_prosjekt.ui.uistate.MapUIState
import com.mapbox.geojson.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapViewModel : ViewModel() {

    private val _lastUserLocation = MutableStateFlow<Point?>(null)

    private val _mapClickedCoordinatesUIState : MutableStateFlow<MapUIState.mapCoordinates> = MutableStateFlow(MapUIState.mapCoordinates())
    val mapClickedCoordinates: StateFlow<MapUIState.mapCoordinates> = _mapClickedCoordinatesUIState

    private var _showMap = MutableStateFlow(false)
    var showMap: StateFlow<Boolean> = _showMap

    fun showMap(show: Boolean) {
        _showMap.value = show
    }

    fun setLastUserLocation(point: Point){
        _lastUserLocation.value = point
    }

    fun getLastUserLocation(): MutableStateFlow<Point?>{
        return _lastUserLocation
    }
    fun updateCoordinates(lat: Double, long: Double) {
        _mapClickedCoordinatesUIState.value = MapUIState.mapCoordinates(lat, long)
    }
}

