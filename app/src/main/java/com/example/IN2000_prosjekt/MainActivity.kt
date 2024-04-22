package com.example.IN2000_prosjekt

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.IN2000_prosjekt.model.weather.MapViewModel
import com.example.IN2000_prosjekt.ui.screens.HomeScreen
import com.example.IN2000_prosjekt.ui.screens.WeatherScreen
import com.example.IN2000_prosjekt.ui.screens.showMap
import com.example.IN2000_prosjekt.ui.theme.IN2000_prosjektTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IN2000_prosjektTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // Greeting("Android")
                    Screen()


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IN2000_prosjektTheme {
        Greeting("Android")
    }
}

@Composable
fun Screen() {
    val navController = rememberNavController()
    val mapViewModel = viewModel<MapViewModel>()

    NavHost(
        navController = navController,
        startDestination = "HomeScreen") {

        composable("HomeScreen") {
            HomeScreen(navController)
        }
        composable("MapScreen") {
            showMap(navController, mapViewModel)
        }

        composable("WeatherScreen"){
            WeatherScreen(navController)
        }

    }
}