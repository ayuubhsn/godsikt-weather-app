
package com.example.IN2000_prosjekt.view.screens

import NavigationMenu
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.IN2000_prosjekt.viewmodel.weather.MapViewModel
import com.example.IN2000_prosjekt.view.components.mapComponents.MapViewContainer
import com.example.IN2000_prosjekt.view.components.mapComponents.MapboxMapComponent
import com.example.IN2000_prosjekt.view.components.mapComponents.addDybdedataLayer
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.IN2000_prosjekt.R
import com.example.IN2000_prosjekt.viewmodel.weather.AppViewModel
import com.example.IN2000_prosjekt.view.components.mapComponents.MapboxPin
import com.example.IN2000_prosjekt.view.components.mapComponents.MapboxUserLocation
import com.example.IN2000_prosjekt.viewmodel.weather.WeatherCardInfo
import kotlinx.coroutines.DelicateCoroutinesApi

@OptIn(DelicateCoroutinesApi::class, ExperimentalComposeUiApi::class)
@Composable
fun showMap(
    navController: NavController,
    mapViewModel: MapViewModel,
    appViewModel: AppViewModel,
    activity: MainActivity,
    modifier: Modifier = Modifier,) {


    var lastOrientation by remember { mutableIntStateOf(Configuration.ORIENTATION_UNDEFINED) }
    val configuration = LocalConfiguration.current

    LaunchedEffect(configuration.orientation) {
        if (lastOrientation != configuration.orientation) {
            // Handle orientation change
            lastOrientation = configuration.orientation
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    Log.d("Orientation", "landscape")
                }
                Configuration.ORIENTATION_PORTRAIT -> {
                    Log.d("Orientation", "portrait")
                }
                Configuration.ORIENTATION_UNDEFINED -> {
                    Log.d("Orientation", "undefined")
                }
            }
        }
    }


    //  val locationComponentEnabled by mapViewModel.locationComponentEnabled.collectAsState()
    val mapViewContainer = MapViewContainer()
    var isWeatherOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var isSOSOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var isInfoOpen by rememberSaveable {
        mutableStateOf(false)
    }

    val userLocation = MapboxUserLocation().apply{
        onLastLocation = { point ->
            // Handle the position change here
            // For example, update your UI or perform other actions based on the new location
            Log.d("UserLocation", "New position: $point")
            // Ignore emulator position that is less than 0 (outside norway)
            mapViewModel.setLastUserLocation(point)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MapboxMapComponent(
            mapViewContainer = mapViewContainer,
            initialCameraOptions = CameraOptions.Builder()
                .center(
                    Point.fromLngLat(
                        8.3,
                        62.0
                    )
                ) // Example: New York City coordinates
                .zoom(4.6)
                .bearing(0.0)
                .pitch(0.0)
                .build(),
            onMapReady = {
                // This block will be executed once the map is ready

                Log.d("MapboxMapComponent", "Map is ready!") // Log that the map is ready
                Log.d("Mapscreen" , "Map is ready")
                val mapboxMap = mapViewContainer.mapView?.mapboxMap
                mapViewContainer.mapView?.let { mapViewModel.setMapboxView(it) }

                if (mapboxMap != null) {
                    addDybdedataLayer(mapboxMap)
                    Log.d("mapscreen", "mapboxmap if test check")

                    userLocation.initUserLocationComponent(mapViewContainer.mapView!!)

                    MapboxPin(mapViewContainer.mapView!!, activity , R.drawable.location_blue) { point ->
                        Log.d("MapScreen", "Map clicked at: ${point.longitude()}, ${point.latitude()}")
                        isWeatherOpen=false
                        mapViewModel.updateCoordinates(lat = point.latitude(), long = point.longitude())
                    }
                }
            }
        )
        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            NavigationMenu(
                navController = navController,
                modifier = Modifier.align(Alignment.BottomCenter))
        }
    }

    if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .offset(y = (-100).dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.End,
                modifier = Modifier.semantics {
                    isTraversalGroup = true
                    contentDescription = "knapper for kart"
                }
            ) {
                // SOS Button
                val LighterRed = Color(0xFFCC444B)
                val infoColor = Color(0xFF4169E1)
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(25))
                        .background(LighterRed)
                        .clickable {
                            isSOSOpen = !isSOSOpen
                        }
                        .semantics {
                            contentDescription = "S O S, knapp 1 av 4"
                            traversalIndex = 1F
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "SOS",
                            fontSize = 14.sp,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .semantics { invisibleToUser() }

                            )

                    }
                }
                // Information button
                FloatingActionButton(
                    onClick = {
                        isInfoOpen = !isInfoOpen
                    },
                    contentColor = Color.White,
                    containerColor = infoColor,
                    modifier = Modifier
                        .size(60.dp)
                        .semantics {
                            traversalIndex = 2F
                        }
                ) {
                    Icon(
                        painterResource(id = R.drawable.info_circle),
                        contentDescription = "Instruksjoner om app, knapp 2 av 4"
                    )
                }

                // Weather information button
                FloatingActionButton(
                    onClick = {
                        isWeatherOpen=true
                    },
                    contentColor = Color.White,
                    modifier = Modifier
                        .size(60.dp)
                        .semantics {
                            traversalIndex = 3F
                        }
                ) {
                    Icon(
                        painterResource(id = R.drawable.cloud_sunny),
                        contentDescription = "Værmelding og farevarsler, knapp 3 av 4"
                    )
                }


                // Centering button
                FloatingActionButton(
                    onClick = {
                        mapViewModel.getMapboxView().value?.mapboxMap?.let {
                            mapViewContainer.setCameraLocation(
                                it, mapViewModel.getLastUserLocation().value)
                        }
                    },
                    //backgroundColor = Color.Blue, // Customize FAB background color
                    contentColor = Color.White, // Customize FAB content color
                    modifier = Modifier
                        .size(60.dp) // Set size of the FAB
                        .semantics {
                            traversalIndex = 4F
                        }
                ) {
                    Icon(
                        painterResource(id = R.drawable.center),
                        contentDescription = "Sentrer skjerm, knapp 4 av 4"
                    )
                }
            }
        }
        if(isWeatherOpen){
            WeatherCardInfo(mapViewModel, appViewModel)
        }
        if (isSOSOpen) {
            SOSCard(
                onConfirm = {
                    // Handle confirmation action here
                    // For example, you can trigger a function to send SOS message
                },
                onClose = {
                    isSOSOpen = false // Close the SOS card
                }
            )
        }
        if (isInfoOpen) {
            InformationCard(onClose = {
                isInfoOpen = false // Close the information card
            })
        }
    }
}

@Composable
fun SOSCard(onConfirm: () -> Unit, onClose: () -> Unit) {
    var showConfirmation by rememberSaveable { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF262626)
        ),
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 290.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showConfirmation) {
                Text(
                    text = "Kystvakten er varslet!",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onClose() // Close the card
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF6358DC)
                    ),
                    modifier = Modifier
                        .width(120.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "Lukk")
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.warning_fill0_wght400_grad0_opsz24),
                        contentDescription = "Warning",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ADVARSEL",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Bekreft at du ønsker å dele din GPS posisjon med kystvakten.",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            onConfirm()
                            showConfirmation = true // Show confirmation message
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Black,
                            containerColor = Color.Red
                        ),
                        modifier = Modifier
                            .width(120.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "BEKREFT")
                    }
                    Button(
                        onClick = {
                            onClose() // Close the card
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color(0xFF6358DC).copy(alpha = 0.45f)
                        ),
                        modifier = Modifier
                            .width(120.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "Avbryt")
                    }
                }
            }
        }
    }
}

@Composable
fun InformationCard(onClose: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF262626)
        ),
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 110.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "App instrukser",
                color = Color.White ,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Her er alle knappene i appen og hva de gjør:",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            InformationItem(
                icon = painterResource(id = R.drawable.sos_fill0_wght400_grad0_opsz24__1_),
                description = "SOS tilkaller kystvakten i faresituasjoner"
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.25f)
            )
            InformationItem(
                icon = painterResource(id = R.drawable.cloud_sunny),
                description = "Viser til været i valgt lokasjon (trykk på kart)"
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.25f)
            )
            InformationItem(
                icon = painterResource(id = R.drawable.center),
                description = "Sentrerer kartet i forhold til posisjon"
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.25f)
            )
            InformationItem(
                icon = painterResource(id = R.drawable.mapicon),
                description = "Viser kartet"
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.25f)
            )
            InformationItem(
                icon = painterResource(id = R.drawable.signicon),
                description = "Viser viktige skilt og regler"
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.25f)
            )
            InformationItem(
                icon = painterResource(id = R.drawable.logo),
                description = "Viser informasjon om appen"
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.25f)
            )
            Button(
                onClick = { onClose() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFF6358DC)
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Lukk")
            }
        }
    }
}


@Composable
fun InformationItem(icon: Painter, description: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

