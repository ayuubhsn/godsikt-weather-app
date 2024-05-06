
package com.example.IN2000_prosjekt.ui.map

import android.util.Log
import com.mapbox.maps.MapView
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.mapbox.geojson.Point
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.ImageHolder
import com.mapbox.maps.extension.style.expressions.dsl.generated.interpolate
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener


/**
 * Initializes and customizes the user's location component on the map.
 *
 * @param mapView The MapView to add the location component to.
 * @param context The context, used for accessing resources.
 */

class MapboxUserLocation(){

    var onLastLocation: ((Point) -> Unit)? = null
    var mapBoxMapView: MapView? = null

    public fun initUserLocationComponent(mapView: MapView) {
        Log.d("initUserLocation", "Enter")
        mapBoxMapView = mapView
        if(mapBoxMapView != null){
            Log.d("goToLocation","goToLocation mapboxmapview is not null")
        }
        // Check if location permissions are granted
        if (ContextCompat.checkSelfPermission(mapView.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("initUserLocation", "Location permission not granted")
            // Consider invoking permission request logic here or notify the user
            return
        }

        val locationComponent = mapView.location

        val locationPuck = LocationPuck2D(
            topImage = ImageHolder.from(com.mapbox.maps.plugin.locationcomponent.R.drawable.mapbox_user_icon),
            bearingImage = ImageHolder.from(com.mapbox.maps.plugin.locationcomponent.R.drawable.mapbox_user_bearing_icon),
            shadowImage = ImageHolder.from(com.mapbox.maps.plugin.locationcomponent.R.drawable.mapbox_user_stroke_icon),
            scaleExpression = interpolate {
                linear()
                zoom()
                stop {
                    literal(0.0)
                    literal(0.6)
                }
                stop {
                    literal(20.0)
                    literal(1.0)
                }
            }.toJson()
        )

        Log.d("initUserLocation", "updateSettings")
        // Apply the custom location puck to the location component
        locationComponent.updateSettings {
            this.enabled = true
            this.locationPuck = locationPuck
            this.puckBearing = PuckBearing.COURSE
        }

        // Add listener for position changes
        locationComponent.addOnIndicatorPositionChangedListener(OnIndicatorPositionChangedListener {
            Log.d("initUserLocation", "Position changed")
            // Handle the new position (e.g., update UI or map camera)
            //mapView.mapboxMap.setCamera(CameraOptions.Builder().center(it).build())
            //mapView.gestures.focalPoint = mapView.mapboxMap.pixelForCoordinate(it)
            onLastLocation?.invoke(it)
        })

        // Add listener for bearing changes
        locationComponent.addOnIndicatorBearingChangedListener(OnIndicatorBearingChangedListener {
            // Handle the new bearing (e.g., adjust UI elements or camera orientation)
            Log.d("initUserLocation", "Bearing changed")
            //mapView.mapboxMap.setCamera(CameraOptions.Builder().bearing(it).build())
        })
    }
}


