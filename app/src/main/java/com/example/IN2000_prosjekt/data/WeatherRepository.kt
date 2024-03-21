package com.example.IN2000_prosjekt.data

class WeatherRepository {
    val dataSourceMetAlert : DataSourceMetAlert = DataSourceMetAlert()
    val dataSourceOceanforecast = DataSourceOceanforecast()

    suspend fun getTemprature() : String?{
        val tempratur = dataSourceOceanforecast.fetchOceanForecast()?.properties?.meta?.units?.sea_water_temperature.toString()
        return  tempratur
    }

    suspend fun CelingAboveSeawater():String?{
        val CelingAboveSeawater =dataSourceMetAlert.fetchMetAlertt().features.getOrNull(0)?.properties?.eventAwarenessName.toString()
        return CelingAboveSeawater
}

    suspend fun sea_water_to_direction (): String?{
        val sea_water_to_direction = dataSourceOceanforecast.fetchOceanForecast().properties.timeseries.getOrNull(0)?.data?.instant?.details?.sea_water_to_direction.toString()
        return sea_water_to_direction
    }



    suspend fun sea_water_speed(): String?{
        val sea_water_speed = dataSourceOceanforecast.fetchOceanForecast()?.properties?.meta?.units?.sea_water_speed.toString()
        return  sea_water_speed
    }


    suspend fun certainty(): String?{
        val certainty  = dataSourceMetAlert.fetchMetAlertt().features.getOrNull(0)?.properties?.certainty.toString()
        return certainty
    }


    suspend fun eventAwarenessName(): String? {
        val eventAwarenessName = dataSourceMetAlert.fetchMetAlertt().features.getOrNull(0)?.properties?.eventAwarenessName.toString()
        return eventAwarenessName
    }

}