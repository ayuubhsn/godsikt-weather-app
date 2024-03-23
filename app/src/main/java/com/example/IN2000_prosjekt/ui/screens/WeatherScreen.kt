package com.example.IN2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.model.weather.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun WeatherCard(map: Map<String, String>){
    val weatherInfo = WeatherInfo()
    var weatherMapState by remember { mutableStateOf<Map<String, String>>(emptyMap())}

    val coroutineScope = rememberCoroutineScope()
    weatherMapState = map

    Column() {

        Spacer(modifier = Modifier.height(110.dp))
        Text(
            text = "Velg område:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )

        Row {
            Spacer(modifier = Modifier.width(10.dp))

            //knapp 1
            IconButton(onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    weatherMapState = weatherInfo.updateWeatherInfo("59.429317", "10.545646")
                }
            }, modifier = Modifier.size(width = 130.dp, height = 60.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Havinfo for oslofjorden",
                        tint = Color(0xFF000080), // Fargen på ikonet
                        modifier = Modifier.size(30.dp) // Størrelsen på ikonet
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // Mellomrom mellom ikonet og teksten
                    Text(
                        text = "Oslofjorden", color = Color.Black, fontSize = 15.sp, style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Gray,
                                blurRadius = 2f,
                                offset = Offset(1f, 1f)
                            )
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(6.dp))

            // knapp 2
            IconButton(onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    weatherMapState = weatherInfo.updateWeatherInfo("59.901320", "10.665752")
                }
            }, modifier = Modifier.size(width = 130.dp, height = 60.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Havinfo for paradisbukta",
                        tint = Color(0xFF000080), // Fargen på ikonet
                        modifier = Modifier.size(30.dp) // Størrelsen på ikonet
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // Mellomrom mellom ikonet og teksten
                    Text(
                        text = "Paradisbukta", color = Color.Black, fontSize = 15.sp, style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Gray,
                                blurRadius = 2f,
                                offset = Offset(1f, 1f)
                            )
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(6.dp))

            //knapp 3
            IconButton(onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    weatherMapState = weatherInfo.updateWeatherInfo("59.139705", "5.269745")
                }
            },modifier = Modifier.size(width = 130.dp, height = 60.dp)) {
               Column (horizontalAlignment = Alignment.CenterHorizontally){
                   Icon(
                       imageVector = Icons.Default.Info,
                       contentDescription = "Havinfo for Ryggen",
                       tint = Color(0xFF000080) , // Fargen på ikonet
                       modifier = Modifier.size(30.dp) // Størrelsen på ikonet
                   )
                   Spacer(modifier = Modifier.width(4.dp)) // Mellomrom mellom ikonet og teksten
                   Text(
                       text = "Ryggen", color = Color.Black, fontSize = 15.sp, style = androidx.compose.ui.text.TextStyle(
                           shadow = Shadow(
                               color = Color.Gray,
                               blurRadius = 2f,
                               offset = Offset(1f, 1f)
                           )
                       )
                   )
               }
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Card(modifier = Modifier
            .height(320.dp)
            .width(390.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)

        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Row {
                    Spacer(modifier = Modifier.height(8.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "HavInfo" , color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold, )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Column {
                Row {
                    Spacer(modifier = Modifier.height(12.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Tempratur i vann", fontSize = 18.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.width(27.dp))

                    Text(text = "Oppmerksomhetsnivå", fontSize = 18.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column {
                Row {
                    Spacer(modifier = Modifier.height(12.dp))
                    Spacer(modifier = Modifier.width(20.dp))

                    val tempratur = weatherMapState["Teampratur"]
                    if (tempratur != null) {
                        Text(text = tempratur, color = Color.Black, fontSize = 18.sp, style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Gray,
                                blurRadius = 2f,
                                offset = Offset(1f, 1f)
                            )
                        )
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(160.dp))
                    val awarnes = weatherMapState["Awareness_level"]

                    if (awarnes != null) {
                        Text(text = awarnes, color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Gray,
                                blurRadius = 2f,
                                offset = Offset(1f, 1f)
                            )
                        ))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Row {
                    Spacer(modifier = Modifier.height(12.dp))
                    Spacer(modifier = Modifier.width(12.dp))

                    Text(text = "Retning av havvann", fontSize = 18.sp,  color = Color.Gray)

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(27.dp))

                    Text(text = "Havstrømhastighet", fontSize = 18.sp,  color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Column {

                Row {
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    val waterDirection = weatherMapState["waterDirection"]

                    if (waterDirection != null) {
                        Text(text = waterDirection, color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Gray,
                                blurRadius = 2f,
                                offset = Offset(1f, 1f)
                            )
                        ))
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(160.dp))
                    val seawaterSpeed = weatherMapState["waterSpeed"]

                    if (seawaterSpeed != null) {
                        Text(text = seawaterSpeed, color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Gray,
                                blurRadius = 2f,
                                offset = Offset(1f, 1f)
                            )
                        ))
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column {

                Row {

                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Forutsigbarhet", fontSize = 18.sp,  color = Color.Gray)

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(45.dp))

                    Text(text = "Beskrivelse av Fare", fontSize = 18.sp,  color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Row {
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    val certainty = weatherMapState["ceartinty"]
                    if (certainty != null) {
                        Text(text = certainty, color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Gray,
                                blurRadius = 2f,
                                offset = Offset(1f, 1f)
                            )
                        ))
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(125.dp))
                    val dangerevent = weatherMapState["event"]

                    if (dangerevent != null) {
                        Text(text = dangerevent, color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Gray,
                                blurRadius = 2f,
                                offset = Offset(1f, 1f)
                            )
                        ))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Column {

                Row {

                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Salinity", fontSize = 18.sp,  color = Color.Gray)

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(115.dp))

                    Text(text = "Tke", fontSize = 18.sp,  color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Row {
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    val salinity =weatherMapState["salinity"]
                    if (salinity != null) {
                        Text(text = salinity, color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Gray,
                                blurRadius = 2f,
                                offset = Offset(1f, 1f)
                            )
                        ))
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(15.dp))
                    val tke = weatherMapState["tke"]

                    if (tke != null) {
                        Text(text = tke, color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Gray,
                                blurRadius = 2f,
                                offset = Offset(1f, 1f)
                            )
                        ))
                    }
                }
            }



        }
    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Tilbake")
                    }
                }
            )
        }
    ) {
        val weatherInfo = WeatherInfo()
        var weatherMapStateInfo by remember { mutableStateOf<Map<String, String>>(emptyMap()) }

        LaunchedEffect(Dispatchers.IO){
            val weathermap = withContext(Dispatchers.IO){
                weatherInfo.updateWeatherInfo()
            }
            weatherMapStateInfo = weathermap


        }

        Spacer(modifier = Modifier.height(5000.dp))
            WeatherCard(weatherMapStateInfo)

    }
}



