package com.example.IN2000_prosjekt.ui.navigationbar

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.R

@Composable
fun NavigationMenu(
    navController: NavController,
    modifier: Modifier = Modifier) {
    //var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Kart"
                )
            },
            label = { Text("Kart") },
            selected = false,
            onClick = { Log.d("Mapscreen", "Onclick in mapscreen") }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Skilt"
                )
            },
            label = { Text("Skilt") },
            selected = false,
            onClick = { Log.d("Mapscreen", "Onclick in mapscreen") }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Profil"
                )
            },
            label = { Text("Profil") },
            selected = false,
            onClick = { navController.navigate("HomeScreen") }
        )
    }
}