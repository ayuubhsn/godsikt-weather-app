package com.example.IN2000_prosjekt.model.weather


import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.IN2000_prosjekt.ui.uistate.MapUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.IN2000_prosjekt.utils.PermissionUserLocation

class MapViewModel : ViewModel() {

    private val _mapClickedCoordinatesUIState : MutableStateFlow<MapUIState.mapCoordinates> = MutableStateFlow(MapUIState.mapCoordinates())
    val mapClickedCoordinates: StateFlow<MapUIState.mapCoordinates> = _mapClickedCoordinatesUIState

    // Tilstand for å spore om applikasjonen har plasseringstillatelse.
    private var _locationPermissionGranted = MutableStateFlow(false)
    var locationPermissionGranted: StateFlow<Boolean> = _locationPermissionGranted

    // Tilstand for å styre visningen av dialog for plasseringstilgang.
    private var _showLocationPermissionDialog = MutableStateFlow(true)
    var showLocationPermissionDialog: StateFlow<Boolean> = _showLocationPermissionDialog

    // Tilstand for å styre om plasseringskomponenten er aktivert.
    private var _locationComponentEnabled = MutableStateFlow(false)
    var locationComponentEnabled: StateFlow<Boolean> = _locationComponentEnabled

    private lateinit var permissionUserLocation: PermissionUserLocation

    fun initializePermissionsManager(activity: Activity) {
        permissionUserLocation = PermissionUserLocation(activity, this)
        permissionUserLocation.checkAndRequestLocationPermissions()
    }


    fun updateLocationPermissions(granted: Boolean) {
        _locationPermissionGranted.value = granted
        _locationComponentEnabled.value = granted
    }
    fun handleRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionUserLocation.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun showLocationPermissionDialog() {
        _showLocationPermissionDialog.value = true
    }

    fun hideLocationPermissionDialog() {
        _showLocationPermissionDialog.value = false
    }



    fun updateCoordinates(lat: Double, long: Double) {
        _mapClickedCoordinatesUIState.value = MapUIState.mapCoordinates(lat, long)
    }

}
