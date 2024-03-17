package com.example.IN2000_prosjekt.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get

import io.ktor.serialization.gson.gson

class DataSourceHavvarsel {
    private val client = HttpClient() {
        defaultRequest {
            url("https://api.havvarsel.no/apis/duapi/havvarsel/v2/")
        }
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun fetchHavvarsel(lat: String, lon: String, depth: String): HavvarselData{
        val coordinates = "$lat/$lon?depth=$depth"
        val havvarselResponse = client.get("dataprojection/temperature%2Csalinity%2CUwind%2CVwind%2Cwind_direction%2Cwind_length%2Cwind_northwards%2Cwind_eastwards%2Cw%2Ccurrent_direction%2Ccurrent_length%2Ctke%2Cu_eastward%2Cv_northward/$coordinates")
        return havvarselResponse.body()
    }
}

