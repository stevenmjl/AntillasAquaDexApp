package edu.ucne.antillasaquadexapp.data.mappers

import edu.ucne.antillasaquadexapp.data.remote.dto.ClimaDto
import edu.ucne.antillasaquadexapp.domain.model.Clima

fun ClimaDto.toDomain() = Clima(
    condicion = mapWeatherCode(current.weatherCode),
    temperatura = current.temperature,
    vientoVelocidad = current.windSpeed,
    humedad = current.humidity,
    iconoUrl = ""
)

private fun mapWeatherCode(code: Int): String {
    return when (code) {
        0 -> "Despejado"
        1, 2, 3 -> "Parcialmente nublado"
        45, 48 -> "Niebla"
        51, 53, 55 -> "Llovizna"
        61, 63, 65 -> "Lluvia"
        71, 73, 75 -> "Nieve"
        80, 81, 82 -> "Chubascos"
        95, 96, 99 -> "Tormenta"
        else -> "Desconocido"
    }
}
