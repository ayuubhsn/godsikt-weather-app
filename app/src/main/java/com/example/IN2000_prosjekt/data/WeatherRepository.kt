package com.example.IN2000_prosjekt.data

import DataSourceLocationforecast
import android.util.Log
import com.example.IN2000_prosjekt.ui.LocationInfo
import com.example.IN2000_prosjekt.ui.MetAlert


class WeatherRepository {
    val dataSourceMetAlert : DataSourceMetAlert = DataSourceMetAlert()
    val dataSourceOceanforecast : DataSourceOceanforecast = DataSourceOceanforecast()
    val dataSourceHavvarsel: DataSourceHavvarsel = DataSourceHavvarsel()
    val dataSourceLocationforecast : DataSourceLocationforecast =DataSourceLocationforecast()
    suspend fun getTemprature(lat:String= "60.10", lon:String = "5") : String{
        val tempratur = dataSourceOceanforecast.fetchOceanForecast(lat,lon).properties.timeseries.getOrNull(0)?.data?.instant?.details?.sea_water_temperature
        return  tempratur.toString()
    }

    suspend fun getAwareness_level(lat: String="60.10",lon: String= "5"):String {
        val CelingAboveSeawater = dataSourceMetAlert.fetchMetAlert(lat,lon).features.firstOrNull()?.properties?.awareness_level.toString()
        return CelingAboveSeawater
    }

    suspend fun getSea_water_to_direction (lat:String = "60.10",lon:String = "5"): String{
        val sea_water_to_direction = dataSourceOceanforecast.fetchOceanForecast(lat,lon).properties.timeseries.getOrNull(0)?.data?.instant?.details?.sea_water_to_direction.toString()
        return sea_water_to_direction
    }



    suspend fun getSea_water_speed(lat:String = "60.10",lon:String = "5"): String{
        val sea_water_speed = dataSourceOceanforecast.fetchOceanForecast(lat,lon)?.properties?.timeseries?.getOrNull(0)?.data?.instant?.details?.sea_water_speed.toString()
        return  sea_water_speed
    }


    suspend fun getCertainty(lat: String= "60.10",lon: String="5"): String{
        val certainty  = dataSourceMetAlert.fetchMetAlert(lat,lon).features.getOrNull(0)?.properties?.certainty.toString()
        return certainty
    }


    suspend fun getEventAwarenessName(lat: String="60.10",lon: String="5"): String{
        val eventAwarenessName = dataSourceMetAlert.fetchMetAlert(lat,lon).features.getOrNull(0)?.properties?.eventAwarenessName.toString()
        return eventAwarenessName
    }
    suspend fun getSalinity(lat:String = "60.10",lon:String = "5",depth: String="0"):String {
        val salinity = dataSourceHavvarsel.fetchHavvarsel(lon,lat, depth)?.data?.getOrNull(1)?.data?.getOrNull(1)?.value.toString()
        return salinity
    }

    suspend fun getTke(lat:String = "60.10",lon:String = "5", depth:String="0"):String {
        val Tke = dataSourceHavvarsel.fetchHavvarsel(lon,lat,depth)?.data?.getOrNull(1)?.data?.getOrNull(11)?.value.toString()
        return Tke
    }


    suspend fun getMetAlert(
        lat: String,
        lon: String
    ): MetAlert {
        val metAlert = dataSourceMetAlert.fetchMetAlert( lon,lat)

        val info =
            metAlert.features.firstOrNull()?.properties?.title ?: "No title available"
        val color =
            metAlert.features.firstOrNull()?.properties?.riskMatrixColor ?: "No color available"
        val level =
            metAlert.features.firstOrNull()?.properties?.awareness_level
        val description =
            metAlert.features.firstOrNull()?.properties?.description

        return MetAlert(
            riskMatrixColor = color,
            description = description!!
        )
    }


    suspend fun getLocation(
        lat: String,
        lon: String
    ): LocationInfo {
        val locationForecast = dataSourceLocationforecast.fetchLocationforecast(lat, lon)

        val temp =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.instant?.details?.air_temperature?.toInt()
        val wind =
            locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_speed
        val rain =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.next_1_hours?.details?.precipitation_amount

        val rainSymbol =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.next_1_hours?.summary?.symbol_code
        val fog =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.instant?.details?.fog_area_fraction
        val windLocation =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.instant?.details?.wind_from_direction

        Log.d("rep", "$rain")
        Log.d("rep", "$wind")
        Log.d("rep", "$temp")
        Log.d("rep", "$windLocation")


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



