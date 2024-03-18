package com.example.IN2000_prosjekt.data

class WeatherRepository {
    val dataSourceMetAlert : DataSourceMetAlert = DataSourceMetAlert()

    fun getTemprature(lat: String,lan :String) : Float{
        dataSourceMetAlert.fetchMetAlert() //mer senere for rikitg variabel
    }

    fun getSunset(lat:String, lan: String){
        dataSourceMetAlert.fetchMetAlert() // skriv riktig sener for å hente riktig variabel
}

    fun getSunrise(lat:String, lan: String){
        dataSourceMetAlert.fetchMetAlert() // skriv riktig sener for å hente riktig variabel
    }



    fun getWindStrength(lat:String, lan: String){
        dataSourceMetAlert.fetchMetAlert() // skriv riktig sener for å hente riktig variabel
    }


    fun getWindDirection(lat:String, lan: String){
        dataSourceMetAlert.fetchMetAlert() // skriv riktig sener for å hente riktig variabel
    }


    fun getWaterDepth(lat:String, lan: String){
        dataSourceMetAlert.fetchMetAlert() // skriv riktig sener for å hente riktig variabel
    }


}