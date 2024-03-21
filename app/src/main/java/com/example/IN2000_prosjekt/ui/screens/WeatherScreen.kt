package com.example.IN2000_prosjekt.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.IN2000_prosjekt.data.DataSourceMetAlert
import com.example.IN2000_prosjekt.data.DataSourceOceanforecast
import com.example.IN2000_prosjekt.model.weather.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(map:Map<String,String>){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Card(modifier = Modifier
            .height(250.dp)
            .width(350.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
            ,
            colors = CardDefaults.cardColors(containerColor = Color.White)

        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Row {
                    Spacer(modifier = Modifier.height(8.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Værdata", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold, )
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

                    val tempratur = map["Teampratur"]
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
                    val awarnes = map["Awareness_level"]

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
                    val waterDirection = map["waterDirection"]

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
                    val seawaterSpeed = map["waterSpeed"]

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
                    val certainty = map["ceartinty"]
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
                    val dangerevent = map["event"]

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


        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun WeatherScreen(){

    val weatherInfo = WeatherInfo()
    val weatherMapState = remember { mutableStateOf<Map<String, String>>(emptyMap()) }

    LaunchedEffect(Dispatchers.IO){
        weatherMapState.value = withContext(Dispatchers.IO){
            weatherInfo.updateWeatherInfo()
        }
    }
    Log.i("Weather", weatherMapState.value.toString())


    WeatherCard(weatherMapState.value)


}



