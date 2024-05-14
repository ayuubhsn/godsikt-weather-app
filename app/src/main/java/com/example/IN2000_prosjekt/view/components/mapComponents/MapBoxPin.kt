package com.example.IN2000_prosjekt.view.components.mapComponents


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.extension.style.layers.properties.generated.IconAnchor

class MapboxPin(private val mapView: MapView, private val context: Context, drawableImageId:Int, private val onMapClick: (point: Point) -> Unit) {

    private var pointAnnotationManager: PointAnnotationManager = mapView.annotations.createPointAnnotationManager()
    private val imageId = "location-marker-icon"

    init {
        Log.d("MapboxPin", "init")

        mapView.mapboxMap.getStyle { style ->
            // Add the icon image to the current style if it's not already added
            Log.d("MapboxPin", "checkstyle")
            if (!style.styleSourceExists(imageId)) {
                Log.d("MapboxPin", "add bitmap")
                val bitmap = convertDrawableToBitmap(drawableImageId) ?: return@getStyle
                style.addImage(imageId, bitmap)
            }
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        mapView.mapboxMap.addOnMapClickListener { point ->
            Log.d("MapboxPin", "Map clicked at: ${point.longitude()}, ${point.latitude()}")

            // Remove existing annotations to ensure only one pin on the map at a time
            pointAnnotationManager.deleteAll()

            Log.d("MapboxPin", "Before annotation options")
            // Create and add a new point annotation at the clicked location
            val pointAnnotationOptions = PointAnnotationOptions()
                .withPoint(Point.fromLngLat(point.longitude(), point.latitude()))
                .withIconImage(imageId)
                .withIconSize(1.5) // Example: 2.0 makes the icon twice its original size
                .withIconAnchor(IconAnchor.BOTTOM)
                .withIconOffset(listOf(0.0, 4.0)) // Offset the icon 4 pixels down

            Log.d("MapboxPin", "Before annotation manager")

            pointAnnotationManager.create(pointAnnotationOptions)
            Log.d("MapboxPin", "CreatePointAnnotation")

            mapView.mapboxMap.setCamera(CameraOptions.Builder().center(point).build())
            //mapView.gestures.focalPoint = mapView.mapboxMap.pixelForCoordinate(it)

            onMapClick(point);
            true // Indicate that the click event has been handled
        }
    }

    private fun convertDrawableToBitmap(drawableId: Int): Bitmap? {
        val drawable = context.resources.getDrawable(drawableId, context.theme)
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        } else {
            Log.d("MapboxPin", "convertDrawableToBitmap")
            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            drawable.draw(canvas)
            return bitmap
        }
    }
}
