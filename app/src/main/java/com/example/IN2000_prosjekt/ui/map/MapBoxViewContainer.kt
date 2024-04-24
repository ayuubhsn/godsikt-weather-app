package com.example.IN2000_prosjekt.ui.map

// MapboxMapComponent.kt
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.maps.*
import com.mapbox.common.MapboxOptions
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.atmosphere.generated.atmosphere
import com.mapbox.maps.extension.style.sources.generated.rasterDemSource
import com.mapbox.maps.extension.style.sources.addSource
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.sources.generated.rasterDemSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.extension.style.terrain.generated.terrain
import com.mapbox.maps.extension.style.layers.generated.skyLayer
import com.mapbox.maps.extension.style.layers.properties.generated.ProjectionName
import com.mapbox.maps.extension.style.layers.properties.generated.SkyType
import com.mapbox.maps.extension.style.projection.generated.projection


class MapViewContainer(initialMapView: MapView? = null) {
    private var _mapView: MapView? = initialMapView

    var mapView: MapView?
        get() = _mapView
        private set(value) {
            _mapView = value
            isMapViewReady = value != null
        }

    // This is now directly modifying the backing property, avoiding the clash
    var isMapViewReady by mutableStateOf(initialMapView != null)
        private set

    fun setMapViewInstance(mapView: MapView?) {
        this.mapView = mapView // This now calls the private setter
    }
}


@Composable
fun MapboxMapComponent(
    modifier: Modifier = Modifier.fillMaxSize(),
    mapViewContainer: MapViewContainer, // Pass this container to hold the MapView
    initialCameraOptions: CameraOptions? = null // Optional: allows you to set an initial camera position
) {
    val SOURCE = "TERRAIN_SOURCE"
    val SKY_LAYER = "sky"
    val TERRAIN_URL_TILE_RESOURCE = "mapbox://mapbox.mapbox-terrain-dem-v1"
    val TERRAIN_EXEGERATION = 1.4

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            MapView(ctx).also { mapView ->
                mapViewContainer.setMapViewInstance(mapView) // Store the MapView instance
                mapView.mapboxMap.loadStyle(style(style = Style.TRAFFIC_NIGHT) {
                    +rasterDemSource(SOURCE) {
                        url(TERRAIN_URL_TILE_RESOURCE)
                        // 514 specifies padded DEM tile and provides better performance than 512 tiles.
                        tileSize(514)


                    }
                    +terrain(SOURCE) {
                        exaggeration(TERRAIN_EXEGERATION)
                    }
                    +skyLayer(SKY_LAYER){
                        skyType(SkyType.ATMOSPHERE)
                        skyAtmosphereSun(listOf(0.0, 90.2))
                    }
                    +atmosphere { }
                    +projection(ProjectionName.GLOBE)
                })
                initialCameraOptions?.let { cameraOptions ->
                    mapView.mapboxMap.setCamera(cameraOptions)
                }

            }
        }
    )
}
