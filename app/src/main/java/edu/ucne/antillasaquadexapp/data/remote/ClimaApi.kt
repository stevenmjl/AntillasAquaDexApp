package edu.ucne.antillasaquadexapp.data.remote

import edu.ucne.antillasaquadexapp.data.remote.dto.ClimaDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ClimaApi {
    @GET("v1/forecast")
    suspend fun getClima(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current") current: String = "temperature_2m,relative_humidity_2m,wind_speed_10m,weather_code"
    ): ClimaDto
}
