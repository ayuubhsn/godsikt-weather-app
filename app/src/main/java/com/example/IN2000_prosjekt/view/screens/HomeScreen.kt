package com.example.IN2000_prosjekt.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                .offset(y = (screenHeight * -0.01).dp)
                .semantics { heading() },
            text = "GOD SIKT",
            fontSize = 70.sp,
            fontWeight = FontWeight.ExtraLight,
        )
        Text(
            modifier = Modifier
                .offset(y = (screenHeight * 0.01).dp),
            text = "Tillattelsesbehov",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(0.2f),
            thickness = 1.dp,
            color = Color.White.copy(alpha = 0.25f)

        )
        Text(
            modifier = Modifier,
            text = "Applikasjonen trenger presis plasseringstillatelse for å fungere",
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraLight,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp

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
