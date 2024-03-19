package com.example.IN2000_prosjekt.ui.Screen

import androidx.compose.ui.graphics.Color
import com.example.IN2000_prosjekt.data.DataSourceMetAlert
import com.example.IN2000_prosjekt.data.DataSourceOceanforecast
import kotlin.random.Random

class ApiViewmodel {
    val dataSourceMetAlert: DataSourceMetAlert = DataSourceMetAlert()
    val dataSourceOceanforecast: DataSourceOceanforecast = DataSourceOceanforecast()

    suspend fun oceanforcastapi(): String? {
        val forecast = dataSourceOceanforecast.fetchOceanForecast().properties.timeseries.getOrNull(1)?.data?.instant?.details?.toString()
        return forecast
    }
    suspend fun metalertapi():String{
        val metAlert = dataSourceMetAlert.fetchMetAlertt().features.getOrNull(0)?.geometry?.coordinates?.getOrNull(0)?.getOrNull(0).toString()
        return metAlert

    }
    fun randomColor(): Color {
        val minValue = 100 // Legg til en minimum verdi for å sikre at farger er mer lysende
        return Color(
            red = Random.nextInt(256 - minValue) + minValue,
            green = Random.nextInt(256 - minValue) + minValue,
            blue = Random.nextInt(256 - minValue) + minValue,
            alpha = 255 // Fullt ugjennomsiktig
        )
    }
}