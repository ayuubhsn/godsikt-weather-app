package com.example.IN2000_prosjekt.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.MainActivity
import com.example.IN2000_prosjekt.model.weather.MapViewModel
import com.example.IN2000_prosjekt.ui.map.MapViewContainer
import com.example.IN2000_prosjekt.ui.map.MapboxMapComponent
import com.mapbox.common.toValue
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun showMap(
        navController: NavController,
        mapViewModel: MapViewModel,
        activity: MainActivity,
        modifier: Modifier = Modifier,) {


      //  val locationComponentEnabled by mapViewModel.locationComponentEnabled.collectAsState()
        val mapViewContainer = MapViewContainer()
        val mapCoordinates by mapViewModel.mapClickedCoordinates.collectAsState()



        Column {
            MapboxMapComponent(
                mapViewContainer = mapViewContainer,
                initialCameraOptions = CameraOptions.Builder()
                    .center(
                        Point.fromLngLat(
                            mapCoordinates.currentScreenLat,
                            mapCoordinates.currentScreenLong
                        )
                    ) // Example: New York City coordinates
                    .zoom(8.0)
                    .bearing(0.0)
                    .pitch(70.0)
                    .build()
            )
                }
            }