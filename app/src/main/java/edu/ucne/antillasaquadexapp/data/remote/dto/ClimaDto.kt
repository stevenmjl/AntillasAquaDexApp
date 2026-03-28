package edu.ucne.antillasaquadexapp.data.remote.dto

import com.squareup.moshi.Json

data class ClimaDto(
    @Json(name = "current") val current: CurrentClimaDto
)

data class CurrentClimaDto(
    @Json(name = "temperature_2m") val temperature: Double,
    @Json(name = "relative_humidity_2m") val humidity: Int,
    @Json(name = "wind_speed_10m") val windSpeed: Double,
    @Json(name = "weather_code") val weatherCode: Int
)
