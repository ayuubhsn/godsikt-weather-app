
package com.example.IN2000_prosjekt.model.weather

import com.example.IN2000_prosjekt.data.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class WeatherInfo {
    var temperature : String = ""
    var Awareness_level : String = ""
    var sea_water_to_direction : String = ""
    var sea_water_speed : String = ""
    var certainty : String = ""
    var salinity: String = ""
    var tke : String = ""
    var eventAwarenessName : String = ""
    val weatherRepository = WeatherRepository()

        suspend fun updateWeatherInfo (lat:String = "60.10",lon:String = "5"):  Map<String, String>  {
            var weathermap = mutableMapOf<String, String>()
            val _weathermap = MutableStateFlow<Map<String, String>>(emptyMap())


            withContext(Dispatchers.IO){
                weathermap.clear()
                temperature = weatherRepository.getTemprature(lat,lon)
                weathermap["Teampratur"] = temperature
                Awareness_level = weatherRepository.getAwareness_level()
                weathermap["Awareness_level"] = Awareness_level
                sea_water_to_direction = weatherRepository.getSea_water_to_direction(lat, lon)
                weathermap["waterDirection"] = weatherRepository.getSea_water_to_direction(lat, lon)
                sea_water_speed = weatherRepository.getSea_water_speed(lat, lon)
                weathermap["waterSpeed"] = weatherRepository.getSea_water_speed(lat, lon)
                certainty = weatherRepository.getCertainty()
                weathermap["ceartinty"] = certainty
                eventAwarenessName= weatherRepository.getEventAwarenessName()
                weathermap["event"] = eventAwarenessName
                salinity = weatherRepository.getSalinity(lon,lat)
                weathermap["salinity"] = salinity
                tke = weatherRepository.getTke(lon, lat)
                weathermap["tke"] = tke
                _weathermap.emit(weathermap)

            }

        return _weathermap.value

    }

}
