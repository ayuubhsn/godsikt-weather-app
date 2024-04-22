package com.example.IN2000_prosjekt.ui.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.model.weather.MapViewModel
import com.mapbox.common.toValue
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun showMap(
        navController: NavController,
        mapViewModel: MapViewModel,
        modifier: Modifier = Modifier) {

        val mapCoordinates by mapViewModel.mapClickedCoordinates.collectAsState()
        val locationPermissionGranted by mapViewModel.locationPermissionGranted.collectAsState()
        val locationComponentEnabled by mapViewModel.locationComponentEnabled.collectAsState()
        val showLocationPermissionDialog by mapViewModel.showLocationPermissionDialog.collectAsState()

        Log.d("MapScreen", "locationComponentEnabled is now ${locationComponentEnabled.toValue()}")


        Column {
            // TopAppBar med tilbake-knapp
            TopAppBar(
                title = { Text("Home") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Tilbake")
                    }
                }
            )

            Text(
                text = "TRYGG SJØ",
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
            if (showLocationPermissionDialog) {
                val context = LocalContext.current // Hent den nærmeste Context-instansen
                AlertDialog(
                    onDismissRequest = { mapViewModel.hideLocationPermissionDialog() },
                    title = { Text("Tillatelsesbehov") },
                    text = { Text("Applikasjonen trenger plasseringstillatelse for å fungere.") },
                    confirmButton = {
                        Button(onClick = {
                            mapViewModel.initializePermissionsManager(context as Activity)
                            mapViewModel.hideLocationPermissionDialog()
                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { mapViewModel.hideLocationPermissionDialog() }) {
                            Text("Avbryt")
                        }
                    }
                )


                //Log.d("MapScreen", "start")
                 Log.d("MapScreen", "verdien til locationPermissionGranted ${locationPermissionGranted.toValue()} og vedien til locationComponentEnabled ${locationComponentEnabled.toValue()}")
                // Log.d("MapScreen", "locationComponenentEn er ${locationComponentEnabled.toValue()} " )
                if (locationComponentEnabled && locationPermissionGranted) {
                    MapboxMap(
                        Modifier.fillMaxSize(),
                        mapViewportState = MapViewportState().apply {
                            setCameraOptions {
                                zoom(5.0)
                                center(
                                    Point.fromLngLat(
                                        mapCoordinates.currentScreenLat,
                                        mapCoordinates.currentScreenLong
                                    )
                                ) //starter med koordinatene til OSLO
                                pitch(50.0) // vipper vinkelen på kartet
                                bearing(0.0) // bærer retningen kartet peker mot 0 er nord
                            }
                        },
                        onMapClickListener = { point ->
                            Log.d(
                                "MapClickListener",
                                "Latitude: ${point.latitude()}, Longitude: ${point.longitude()}"
                            )
                            mapViewModel.updateCoordinates(point.latitude(), point.longitude())
                            true
                        },

                        )
                }
            }


        }


    }

/*
        @Preview
        @Composable
        fun showMapPreview() {
            //    showMap(modifier = Modifier)
        }
    }
*/