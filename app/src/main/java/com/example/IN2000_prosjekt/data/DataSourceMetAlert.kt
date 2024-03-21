package com.example.IN2000_prosjekt.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson
import io.ktor.util.appendIfNameAbsent

class DataSourceMetAlert() {
    private val client = HttpClient() {
        defaultRequest {
            url("https://gw-uio.intark.uh-it.no/in2000/weatherapi")
            headers.appendIfNameAbsent(
                "X-Gravitee-API-Key",
                "86d1e8ef-7703-4ded-b17a-f168226135cb"
            )
        }
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun fetchMetAlertt(): MetAlertData {
        val metAlertResponse = client.get("weatherapi/metalerts/2.0/current.json")
        return metAlertResponse.body()
    }
}