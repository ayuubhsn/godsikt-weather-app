package com.example.IN2000_prosjekt.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState

class MapScreen {

    @Composable
    fun showMap(modifier: Modifier = Modifier) {
        Column {
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
                                10.7522,
                                59.9139
                            )
                        ) //starter med koordinatene til OSLO
                        pitch(50.0) // vipper vinkelen på kartet
                        bearing(0.0) // bærer retningen kartet peker mot 0 er nord
                    }
                },
            )
        }
    }

    @Preview
    @Composable
    fun showMapPreview(){
        showMap(modifier = Modifier)
    }
}