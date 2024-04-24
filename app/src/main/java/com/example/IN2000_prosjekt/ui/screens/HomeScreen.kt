package com.example.IN2000_prosjekt.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.MainActivity
import com.example.IN2000_prosjekt.R


@Composable
fun HomeScreen(navController: NavController, activity: MainActivity){
    val image = painterResource(R.drawable.sailboat)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment =  Alignment.CenterHorizontally
    ){
        Text(
            text = "Velkommen til Trygg Sjø",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,

        )
        Image(
            painter = image,
            contentDescription = "sailboat",
            contentScale = ContentScale.Fit,
            alpha = 2F

        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            //knapp 1
            Button(
                //onClick ={ navController.navigate("MapScreen") },
                onClick ={ activity.checkAndRequestLocationPermissions() },
                colors = ButtonDefaults.buttonColors(Color(0xFF000080))
            ) {
                Text(text = "Start")
            }

            //knapp 2
            Button(
                onClick = { navController.navigate("WeatherScreen") },
                colors = ButtonDefaults.buttonColors(Color(0xFF000080))
            ) {
                Text(text = "HavInfo")
            }




        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    //HomeScreen()

}


