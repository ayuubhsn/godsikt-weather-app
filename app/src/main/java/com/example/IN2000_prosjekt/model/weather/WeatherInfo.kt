package com.example.IN2000_prosjekt.model.weather

class WeatherInfo{
    var temperature : Float = 0.0f
    var sunrise : String = ""
    var sunset : String = ""
    var windStrength : Float = 0.0f
    var windDirection : String = ""
    var waterDepth : Float = 0.0f

    //update variables for chosen location
    fun updateWeatherInfo (lat : String,lan : String){
        temperature = getTemprature(lat,lan)
        sunrise = getSunrise(lat,lan)
        sunset = getSunset(lat,lan)
        windStrength = getwindStrength(lat,lan)
        windDirection = getWindDirection(lat,lan)
        waterDepth = getWaterDepth(lat,lan)
    }
}