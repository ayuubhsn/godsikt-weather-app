package com.example.IN2000_prosjekt.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.model.weather.MapViewModel
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
                onMapClickListener = {point ->
                    Log.d("MapClickListener", "Latitude: ${point.latitude()}, Longitude: ${point.longitude()}")
                    mapViewModel.updateCoordinates(point.latitude(), point.longitude())
                    true
                }
            )
        }
    }

    @Preview
    @Composable
    fun showMapPreview(){
    //    showMap(modifier = Modifier)
    }
