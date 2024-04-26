package com.example.IN2000_prosjekt.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.MainActivity
import com.example.IN2000_prosjekt.model.weather.MapViewModel
import com.example.IN2000_prosjekt.ui.map.MapViewContainer
import com.example.IN2000_prosjekt.ui.map.MapboxMapComponent
import com.example.IN2000_prosjekt.ui.map.addDybdedataLayer
import com.example.IN2000_prosjekt.ui.navigationbar.NavigationMenu
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.IN2000_prosjekt.R
import com.example.IN2000_prosjekt.ui.map.MapboxUserLocation


@Composable
fun showMap(
navController: NavController,
mapViewModel: MapViewModel,
activity: MainActivity,
modifier: Modifier = Modifier,) {


    //  val locationComponentEnabled by mapViewModel.locationComponentEnabled.collectAsState()
    val mapViewContainer = MapViewContainer()
    val mapCoordinates by mapViewModel.mapClickedCoordinates.collectAsState()
    var mapIsReady = false
    val userLocation = MapboxUserLocation{
        Log.d("Mapscreen",it.toString())
        mapViewModel.setLastUserLocation(it)
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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
                .pitch(0.0)
                .build(),
            onMapReady = {
                // This block will be executed once the map is ready
                Log.d("MapboxMapComponent", "Map is ready!") // Log that the map is ready
                mapIsReady = true
                Log.d("Mapscreen" , "Map is ready")
                val mapboxMap = mapViewContainer.mapView?.mapboxMap

                if (mapboxMap != null) {
                    addDybdedataLayer(mapboxMap)
                    Log.d("mapscreen", "mapboxmap if test check")
                    userLocation.initUserLocationComponent(mapViewContainer.mapView!!)
                }
            }
        )
        NavigationMenu(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .offset(y = (-150).dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.End
        ) {

            // SOS button
            val LighterRed = Color(0xFFCC444B)
            FloatingActionButton(
                onClick = {
                    // SOS logic here
                },
                containerColor = LighterRed,
                contentColor = Color.White,
                modifier = Modifier
                    .size(60.dp)
            ) {
                Text(
                    text = "SOS",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }

            // map layer button
            FloatingActionButton(
                onClick = {
                    //logic for changing map layer button
                },
                contentColor = Color.White,
                modifier = Modifier
                    .size(60.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.depthicon),
                    contentDescription = "MapLayer"
                )
            }


            // Centering button
            FloatingActionButton(
                onClick = { mapViewContainer.mapView?.mapboxMap?.setCamera(CameraOptions.Builder().center(mapViewModel.getLastUserLocation().value).build())
                    //mapViewContainer.mapView?.gestures?.focalPoint = mapBoxMapView!!.mapboxMap.pixelForCoordinate(
                },
                //backgroundColor = Color.Blue, // Customize FAB background color
                contentColor = Color.White, // Customize FAB content color
                modifier = Modifier
                    .size(60.dp) // Set size of the FAB
            ) {
                Icon(
                    painterResource(id = R.drawable.center),
                    contentDescription = null
                )
            }
        }
    }
}

