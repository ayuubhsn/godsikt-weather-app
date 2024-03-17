package com.example.IN2000_prosjekt.data


data class ClosestGridPoint(
    val distance: Double,
    val lat: Double,
    val lon: Double,
    val x: Int,
    val y: Int
)

data class ClosestGridPointWithData(
    val distance: Double,
    val lat: Double,
    val lon: Double,
    val x: Int,
    val y: Int
)

data class DataH(
    val `data`: List<DataX>,
    val rawTime: Double
)

data class DataX(
    val key: String,
    val value: Double
)

data class HavvarselData(
    val closestGridPoint: ClosestGridPoint,
    val closestGridPointWithData: ClosestGridPointWithData,
    val `data`: List<DataH>,
    val metadata: List<Any>,
    val queryPoint: QueryPoint
)

data class QueryPoint(
    val lat: Double,
    val lon: Double
)