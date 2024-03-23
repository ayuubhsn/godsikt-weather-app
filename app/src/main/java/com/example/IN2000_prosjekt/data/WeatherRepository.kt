package com.example.IN2000_prosjekt.data


class WeatherRepository {
    val dataSourceMetAlert : DataSourceMetAlert = DataSourceMetAlert()
    val dataSourceOceanforecast : DataSourceOceanforecast = DataSourceOceanforecast()
    val dataSourceHavvarsel: DataSourceHavvarsel = DataSourceHavvarsel()

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





}