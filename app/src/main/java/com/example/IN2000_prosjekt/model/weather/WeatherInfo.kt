package com.example.IN2000_prosjekt.model.weather

import com.example.IN2000_prosjekt.data.WeatherRepository

class WeatherInfo{
    var temperature : String = ""
    var CelingAboveSeawater : String = ""
    var sea_water_to_direction : String = ""
    var sea_water_speed : String = ""
    var certainty : String = ""
    var eventAwarenessName : String = ""
    val weatherRepository = WeatherRepository()
     var weathermap = mutableMapOf<String, String>()

    //update variables for chosen location
    suspend fun updateWeatherInfo (lat : String,lan : String): MutableMap<String, String> {
        weathermap.clear()
        temperature = weatherRepository.getTemprature().toString()
        weathermap["Teampratur"] = temperature
        CelingAboveSeawater = weatherRepository.CelingAboveSeawater().toString()
        weathermap["Abovesea"] = CelingAboveSeawater
        sea_water_to_direction = weatherRepository.sea_water_to_direction().toString()
        weathermap["waterDirection"] = weatherRepository.sea_water_to_direction()
        sea_water_speed = weatherRepository.sea_water_speed().toString()
        weathermap["waterSpeed"] = weatherRepository.sea_water_speed()
        certainty = weatherRepository.certainty().toString()
        weathermap["ceartinty"] = certainty
         eventAwarenessName= weatherRepository.eventAwarenessName().toString()
        weathermap["event"] = eventAwarenessName
        return weathermap

    }
}





