package com.example.IN2000_prosjekt.data

import kotlin.math.roundToInt
import kotlin.math.roundToLong

class WeatherRepository {
    val dataSourceMetAlert : DataSourceMetAlert = DataSourceMetAlert()
    val dataSourceOceanforecast : DataSourceOceanforecast = DataSourceOceanforecast()
    val dataSourceHavvarsel = DataSourceHavvarsel()

    suspend fun getTemprature() : String{
        val tempratur = dataSourceOceanforecast.fetchOceanForecast().properties.timeseries.getOrNull(0)?.data?.instant?.details?.sea_water_temperature
        return  tempratur.toString()
    }

    suspend fun getAwareness_level():String {
        val CelingAboveSeawater = dataSourceMetAlert.fetchMetAlertt().features.firstOrNull()?.properties?.awareness_level.toString()
        return CelingAboveSeawater
}

    suspend fun getSea_water_to_direction (): String{
        val sea_water_to_direction = dataSourceOceanforecast.fetchOceanForecast().properties.timeseries.getOrNull(0)?.data?.instant?.details?.sea_water_to_direction.toString()
        return sea_water_to_direction
    }



    suspend fun getSea_water_speed(): String{
        val sea_water_speed = dataSourceOceanforecast.fetchOceanForecast()?.properties?.timeseries?.getOrNull(0)?.data?.instant?.details?.sea_water_speed.toString()
        return  sea_water_speed
    }


    suspend fun getCertainty(): String{
        val certainty  = dataSourceMetAlert.fetchMetAlertt().features.getOrNull(0)?.properties?.certainty.toString()
        return certainty
    }


    suspend fun getEventAwarenessName(): String{
        val eventAwarenessName = dataSourceMetAlert.fetchMetAlertt().features.getOrNull(0)?.properties?.eventAwarenessName.toString()
        return eventAwarenessName
    }
    suspend fun getSalinity():String {
        val salinity = dataSourceHavvarsel.fetchHavvarsel("5.32", "60.39", "0").data.getOrNull(1)?.data?.getOrNull(1)?.value.toString()
        return salinity
    }

    suspend fun getTke():String {
        val Tke = dataSourceHavvarsel.fetchHavvarsel("5.32", "60.39", "0").data.getOrNull(1)?.data?.getOrNull(11)?.value.toString()
        return Tke
    }





}