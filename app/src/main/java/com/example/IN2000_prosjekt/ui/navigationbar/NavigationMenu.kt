package com.example.IN2000_prosjekt.ui.navigationbar

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
                    painterResource(id = R.drawable.mapicon),
                    contentDescription = "Kart"
                )
            },
            label = { Text("Kart") },
            selected = false,
            onClick = { navController.navigate("MapScreen")}
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.signicon),
                    contentDescription = "Skilt"
                )
            },
            label = { Text("Skilt") },
            selected = false,
            onClick = { navController.navigate("CategoryScreen") }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.settingsicon),
                    contentDescription = "Innstillinger"
                )
            },
            label = { Text("Innstillinger") },
            selected = false,
            onClick = { navController.navigate("SettingsScreen") }
        )
    }
}