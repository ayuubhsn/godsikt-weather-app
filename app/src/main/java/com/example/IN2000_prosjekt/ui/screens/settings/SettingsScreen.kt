package com.example.IN2000_prosjekt.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.ui.navigationbar.NavigationMenu


@Composable
fun SettingsScreen(
    navController: NavController
) {

    Surface(color = Color(0xFF17161E), modifier = Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                text = "Instillinger",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 4.sp),
                color = Color.White,
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.height(60.dp))
            Divider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier
                    .width(330.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Row(modifier = Modifier
                .padding(10.dp)
                .width(275.dp)
                .height(45.dp)
                .clickable { navController.navigate("AboutUsScreen")}

            ){
              //her kan denne knappen sette
             Text(
                  text = "Om oss",
                  color = Color.Gray,
                  fontSize = 25.sp,
                  modifier = Modifier
                      .weight(1f)
                      .align(Alignment.CenterVertically)
             )
             Icon(
                     imageVector = Icons.Default.ArrowForward,
                     contentDescription = "ArrowForward Om oss",
                     tint = Color.Gray,
                     modifier = Modifier
                         .align(Alignment.CenterVertically)
                         .size(30.dp) // Set the size of the icon here. Adjust the value as needed.
                 )

            }
            Spacer(modifier = Modifier.height(35.dp))
            Row(modifier = Modifier
                .padding(10.dp)
                .width(275.dp)
                .height(45.dp)
                .clickable { navController.navigate("PrivacyInfoScreen") }

            ){

             Text(
                  text = "Personvern",
                  color = Color.Gray,
                  fontSize = 25.sp,
                  modifier = Modifier
                      .weight(1f)
                      .align(Alignment.CenterVertically)
             )
             Icon(
                     imageVector = Icons.Default.ArrowForward,
                     contentDescription = "ArrowForward Personvern",
                     tint = Color.Gray,
                     modifier = Modifier
                         .align(Alignment.CenterVertically)
                         .size(30.dp) // Set the size of the icon here. Adjust the value as needed.
                 )

            }
            Spacer(modifier = Modifier.height(35.dp))
            Row(modifier = Modifier
                .padding(10.dp)
                .width(275.dp)
                .height(45.dp)
                .clickable {navController.navigate("TermsAndConditionsScreen")  }

            ){

             Text(
                  text = "Vilkår og Betingelser",
                  color = Color.Gray,
                  fontSize = 25.sp,
                  modifier = Modifier
                      .weight(1f)
                      .align(Alignment.CenterVertically)
             )
             Icon(
                     imageVector = Icons.Default.ArrowForward,
                     contentDescription = "ArrowForward Vilkår og Betingelser",
                     tint = Color.Gray,
                     modifier = Modifier
                         .align(Alignment.CenterVertically)
                         .size(30.dp) // Set the size of the icon here. Adjust the value as needed.

                 )

            }
            Spacer(modifier = Modifier.height(35.dp))
            Row(modifier = Modifier
                .padding(10.dp)
                .width(275.dp)
                .height(45.dp)
                .clickable { navController.navigate("AboutDataSourcesScreen") }

            ){

             Text(
                  text = "Om datakilder",
                  color = Color.Gray,
                  fontSize = 25.sp,
                  modifier = Modifier
                      .weight(1f)
                      .align(Alignment.CenterVertically)
             )
             Icon(
                     imageVector = Icons.Default.ArrowForward,
                     contentDescription = "ArrowForward Om datakilder",
                     tint = Color.Gray,
                     modifier = Modifier
                         .align(Alignment.CenterVertically)
                         .size(30.dp)

                 )

            }
            Spacer(modifier = Modifier.weight(1f))
            NavigationMenu(
                navController = navController, Modifier.align(Alignment.CenterHorizontally))}


    }

}

/*
@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}

 */











