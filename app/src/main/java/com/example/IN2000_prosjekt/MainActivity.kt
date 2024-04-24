package com.example.IN2000_prosjekt

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.livedata.observeAsState
import com.example.IN2000_prosjekt.model.weather.MapViewModel
import com.example.IN2000_prosjekt.ui.screens.HomeScreen
import com.example.IN2000_prosjekt.ui.screens.WeatherScreen
import com.example.IN2000_prosjekt.ui.screens.showMap
import com.example.IN2000_prosjekt.ui.theme.IN2000_prosjektTheme
import android.Manifest
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

class SharedViewModel : ViewModel() {
    private val _permissionState = MutableLiveData<Event<String?>>(Event(null))
    val permissionState: LiveData<Event<String?>> = _permissionState

    fun setPermissionState(destination: String?) {
        _permissionState.value = Event(destination)
    }
}

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    // Register the permissions callback, which handles the user's response to the system permissions dialog.
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        // Permission is denied. Handle the failure to obtain permission.// Permission is granted. Continue with setting up location-based features.
        if (isGranted){
            Log.d("GPS Test", "Granted")
            sharedViewModel.setPermissionState("MapScreen")
        }else{
            Log.d("GPS Test", "Not granted")
        }


        //permissionGranted = isGranted
    }

    private val useUserLocation = true

    public fun checkAndRequestLocationPermissions() {
        Log.d("MainActivity", "Check and request user location permission")
        if (useUserLocation) {
            when {
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                    // Explain to the user why you need the permission, and then request it.
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
                else -> {
                    // Directly ask for the permission.
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IN2000_prosjektTheme (darkTheme = true){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Greeting("Android")
                    Screen(this)
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
fun Screen(activity : MainActivity) {
    val navController = rememberNavController()
    val mapViewModel = viewModel<MapViewModel>()
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "HomeScreen") {

        composable("HomeScreen") {
            HomeScreen(navController, activity)
        }
        composable("MapScreen") {
            showMap(navController, mapViewModel, activity)
        }

        composable("WeatherScreen"){
            WeatherScreen(navController)
        }
    }

    val permissionState = sharedViewModel.permissionState.observeAsState()
    LaunchedEffect(permissionState.value) {
        permissionState.value?.getContentIfNotHandled()?.let { destination ->
            if (destination == "MapScreen") {
                navController.navigate(destination)
            }
        }
    }
}