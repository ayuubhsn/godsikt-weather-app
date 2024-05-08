package com.example.IN2000_prosjekt.data.weather

import android.util.Log
import com.example.IN2000_prosjekt.ui.LocationInfo
import com.example.IN2000_prosjekt.ui.MetAlert

class WeatherRepository {
    val dataSourceMetAlert : DataSourceMetAlert = DataSourceMetAlert()
    val dataSourceLocationforecast : DataSourceLocationforecast = DataSourceLocationforecast()

    suspend fun getMetAlert(
        lat: String,
        lon: String
    ): MetAlert {

        Log.d("WeatherRepo", "getMetAlert $lat, $lon")

        val metAlert = dataSourceMetAlert.fetchMetAlert( lon,lat)

        Log.d("WeatherRepo", "datasourcemetalert")

        val info =
            metAlert.features.firstOrNull()?.properties?.title ?: "No title available"

        Log.d("WeatherRepo", "info")

        val color =
            metAlert.features.firstOrNull()?.properties?.riskMatrixColor ?: "No color available"

        Log.d("WeatherRepo", "color")

        val level =
            metAlert.features.firstOrNull()?.properties?.awareness_level

        Log.d("WeatherRepo", "level")

        val description =
            metAlert.features.firstOrNull()?.properties?.description?: "Ingen data tilgjengelig for valgt posisjon"

        Log.d("WeatherRepo", "description")


        return MetAlert(
            riskMatrixColor = color,
            description = description
        )
    }


    suspend fun getLocation(
        lat: String,
        lon: String
    ): LocationInfo {
        Log.d("WeatherRepo", "getLocation")
        val locationForecast = dataSourceLocationforecast.fetchLocationforecast(lat, lon)

        Log.d("WeatherRepo", "getlocation2")

        val temp =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.instant?.details?.air_temperature?.toInt()
        val wind =
            locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_speed
        val rain =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.next_1_hours?.details?.precipitation_amount

        Log.d("WeatherRepo", "$rain")

        val rainSymbol =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.next_1_hours?.summary?.symbol_code
        val fog =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.instant?.details?.fog_area_fraction
        val windLocation =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.instant?.details?.wind_from_direction


        return LocationInfo(
            temperatureL = temp!!,
            fog_area_fraction = fog!!,
            precipitation_amount = rain!!,
            wind_speed = wind!!,
            wind_from_direction = windLocation!!,
            symbol_code = rainSymbol!!

        )
    }

}
