package com.example.IN2000_prosjekt.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.IN2000_prosjekt.data.DataSourceMetAlert
import com.example.IN2000_prosjekt.data.DataSourceOceanforecast
import com.example.IN2000_prosjekt.data.oceanForecastData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Preview
@Composable
fun Oceanforepreviw(){
val Oceaforecast:DataSourceOceanforecast = DataSourceOceanforecast()
var forecast by remember { mutableStateOf("") }
val dataSourceMetAlert:DataSourceMetAlert = DataSourceMetAlert()
var metAlert by remember { mutableStateOf(" ")}




        LaunchedEffect(Unit){
            withContext(Dispatchers.IO){
                forecast = Oceaforecast.fetchOceanForecast().properties.timeseries.getOrNull(1)?.data?.instant?.details?.sea_water_temperature.toString()
                metAlert = dataSourceMetAlert.fetchMetAlertt().features.getOrNull(0)?.geometry?.coordinates?.getOrNull(0)?.getOrNull(0).toString()
                    ?.toString()
                    .toString()

            }
        }
    Column {
        Text(" Tempraturen i sea_water_temperature er : $forecast")
        Text(text = "kordinatet er: $metAlert")

    }

    }
