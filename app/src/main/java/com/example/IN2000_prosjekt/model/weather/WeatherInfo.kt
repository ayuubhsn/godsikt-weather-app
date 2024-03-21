package com.example.IN2000_prosjekt.model.weather

import com.example.IN2000_prosjekt.data.WeatherRepository

class WeatherInfo{
    var temperature : String = ""
    var Awareness_level : String = ""
    var sea_water_to_direction : String = ""
    var sea_water_speed : String = ""
    var certainty : String = ""
    var salinity: String = ""
    var tke : String = ""
    var eventAwarenessName : String = ""
    val weatherRepository = WeatherRepository()

     var weathermap = mutableMapOf<String, String>()

    //update variables for chosen location
    suspend fun updateWeatherInfo (): MutableMap<String, String> {
        weathermap.clear()
        temperature = weatherRepository.getTemprature()
        weathermap["Teampratur"] = temperature
        Awareness_level = weatherRepository.getAwareness_level()
        weathermap["Awareness_level"] = Awareness_level
        sea_water_to_direction = weatherRepository.getSea_water_to_direction()
        weathermap["waterDirection"] = weatherRepository.getSea_water_to_direction()
        sea_water_speed = weatherRepository.getSea_water_speed()
        weathermap["waterSpeed"] = weatherRepository.getSea_water_speed()
        certainty = weatherRepository.getCertainty()
        weathermap["ceartinty"] = certainty
         eventAwarenessName= weatherRepository.getEventAwarenessName()
        weathermap["event"] = eventAwarenessName
        salinity = weatherRepository.getSalinity()
        weathermap["salinity"] = salinity
        tke = weatherRepository.getTke()
        weathermap["tke"] = tke
        return weathermap

    }
}

