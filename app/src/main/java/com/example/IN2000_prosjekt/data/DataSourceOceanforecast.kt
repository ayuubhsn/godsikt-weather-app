package com.example.IN2000_prosjekt.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson
import io.ktor.util.appendIfNameAbsent
import kotlinx.coroutines.runBlocking

class DataSourceOceanforecast() {
    private val client = HttpClient() {
        defaultRequest {
            url("https://gw-uio.intark.uh-it.no/in2000/")
            headers.appendIfNameAbsent(
                "X-Gravitee-API-Key",
                "86d1e8ef-7703-4ded-b17a-f168226135cb"
            )
        }
        install(ContentNegotiation) {
            gson()

        }
    }

    suspend fun fetchOceanForecast(lat:String,lon:String): oceanForecastData {
        val coordinates = "lat=$lat&lon=$lon"
        val oceanResponse = client.get("weatherapi/oceanforecast/2.0/complete?$coordinates")
        return oceanResponse.body<oceanForecastData>()
    }

}