package com.example.IN2000_prosjekt.utils

import android.app.Activity
import android.util.Log

import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.example.IN2000_prosjekt.model.weather.MapViewModel

class PermissionUserLocation(private val activity: Activity, private val mapViewModel: MapViewModel) {
    private val permissionsManager = PermissionsManager(object : PermissionsListener {
        override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        }

        override fun onPermissionResult(granted: Boolean) {
            if (granted) {
                onPermissionsGranted()
            } else {
                onPermissionsDenied()
            }
        }
    })

    fun checkAndRequestLocationPermissions() {
        if (PermissionsManager.areLocationPermissionsGranted(activity)) {
            onPermissionsGranted()
        } else {
            permissionsManager.requestLocationPermissions(activity)
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun onPermissionsGranted() {
        Log.d("PermissionUserLocation", "Plasseringstillatelsene er bekreftet")
        mapViewModel.updateLocationPermissions(true)
    }

    private fun onPermissionsDenied() {
        mapViewModel.updateLocationPermissions(false)
    }
}

