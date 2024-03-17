package com.example.IN2000_prosjekt.data

data class DataO(
    val instant: Instant
)

data class oceanForecastData(
    val geometry: GeometryOceanforecast,
    val properties: PropertiesOceanforecast,
    val type: String
)
data class Details(
    val sea_surface_wave_from_direction: Double,
    val sea_surface_wave_height: Double,
    val sea_water_speed: Double,
    val sea_water_temperature: Double,
    val sea_water_to_direction: Double
)

data class Instant(
    val details: Details
)
data class GeometryOceanforecast(
    val coordinates: List<Double>,
    val type: String
)
data class Meta(
    val units: Units,
    val updated_at: String
)

data class PropertiesOceanforecast(
    val meta: Meta,
    val timeseries: List<Timesery>
)

data class Timesery(
    val `data`: DataO,
    val time: String
)
data class Units(
    val sea_surface_wave_from_direction: String,
    val sea_surface_wave_height: String,
    val sea_water_speed: String,
    val sea_water_temperature: String,
    val sea_water_to_direction: String
)