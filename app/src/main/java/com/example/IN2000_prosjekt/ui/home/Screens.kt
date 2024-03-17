package com.example.IN2000_prosjekt.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.IN2000_prosjekt.data.DataSourceMetAlert
import com.example.IN2000_prosjekt.data.DataSourceOceanforecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ApiCard(img: MutableList<String>, apiViewmodel: ApiViewmodel){
   Scaffold(topBar = {
       CenterAlignedTopAppBar(
           title = {
               Text(
                   "ApiData",
                   fontSize = 20.sp,
                   modifier = Modifier.padding(10.dp)
               )
           }
       )


   }, content = {
       Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Spacer(modifier = Modifier.height(100.dp))
           Card(
               modifier = Modifier
                   .fillMaxWidth()

                   .padding(16.dp)
           ) {
               Column(
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {

                   LazyVerticalGrid(columns = GridCells.Fixed(2),
                       content = {

                           items(img) { foto ->
                               Text(
                                   text = foto,
                                   color = apiViewmodel.randomColor(),
                                   fontSize = 14.sp,
                                   modifier = Modifier.padding(10.dp)

                               )


                           }

                       }
                   )
               }
           }
       }
})
}


@Preview
@Composable
fun ApiScreen(){
    val apiViewmodel:ApiViewmodel = ApiViewmodel()
    val Apiliste = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        val oceanForecast = withContext(Dispatchers.IO) {
            apiViewmodel.oceanforcastapi()
        }
        val metAlert = withContext(Dispatchers.IO) {
            apiViewmodel.metalertapi()
        }

        if (oceanForecast != null) {
            Apiliste.add(oceanForecast)
        }
        Apiliste.add(metAlert)
        Log.i("skrivvv", Apiliste.toString())





    }
    Log.i("nooo", Apiliste.joinToString(separator = ", "))


    ApiCard(img = Apiliste, apiViewmodel)



}















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
