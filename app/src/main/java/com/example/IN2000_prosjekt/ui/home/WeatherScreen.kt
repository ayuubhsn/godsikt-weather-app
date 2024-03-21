package com.example.IN2000_prosjekt.ui.home

import android.os.Build
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.format.TextStyle


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun WeatherCard(){

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
                    Text(text = "Havarsel", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold, )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Column {
                Row {
                    Spacer(modifier = Modifier.height(12.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Tempratur i vann", fontSize = 18.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.width(27.dp))

                    Text(text = "Høyde over havnivå", fontSize = 18.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column {
                Row {
                    Spacer(modifier = Modifier.height(12.dp))
                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "3.0", color = Color.Black, fontSize = 18.sp, style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Gray,
                            blurRadius = 2f,
                            offset = Offset(1f, 1f)
                        )
                    )
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(160.dp))

                    Text(text = "200", color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Gray,
                            blurRadius = 2f,
                            offset = Offset(1f, 1f)
                        )
                    ))
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

                    Text(text = "103.3", color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Gray,
                            blurRadius = 2f,
                            offset = Offset(1f, 1f)
                        )
                    ))

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(160.dp))

                    Text(text = "0.1", color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Gray,
                            blurRadius = 2f,
                            offset = Offset(1f, 1f)
                        )
                    ))
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
                    Text(text = "Likely", color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Gray,
                            blurRadius = 2f,
                            offset = Offset(1f, 1f)
                        )
                    ))

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(120.dp))

                    Text(text = "Sterk ising på skip", color = Color.Black, fontSize = 16.sp, style = androidx.compose.ui.text.TextStyle(
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun WeatherScreen(){
    /*
    val weatherInfo = WeatherInfo()
    var weathermap = mutableMapOf<String, String>()
    LaunchedEffect(Dispatchers.IO){
        weathermap = withContext(Dispatchers.IO){
            weatherInfo.updateWeatherInfo()
        }
    }
    */


    WeatherCard()


}



