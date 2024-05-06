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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
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
    val image = painterResource(R.drawable.logo)
    val screenHeight = with(LocalDensity.current) {
        (LocalConfiguration.current.screenHeightDp * density).toInt()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment =  Alignment.CenterHorizontally
    ){
        Image(
            painter = image,
            contentDescription = "logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size((screenHeight * 0.15).dp),
        )
        Text(
            modifier = Modifier
                .offset(y = (screenHeight * -0.02).dp),
            text = "GOD SIKT",
            fontSize = 70.sp,
            fontWeight = FontWeight.ExtraLight,
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            //knapp 1
            Button(
                //onClick ={ navController.navigate("MapScreen") },
                onClick ={ activity.checkAndRequestLocationPermissions() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFF6358DC)
                ),
                modifier = Modifier
                    .height(80.dp) // Adjust button height
                    .width(200.dp) // Adjust button width
                    .padding(8.dp), // Add padding
                shape = RoundedCornerShape(8.dp) // Adjust corner radius
            ) {
                Text(
                    text = "START",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    //HomeScreen()

}


