package edu.ucne.antillasaquadexapp.domain.model

data class Clima(
    val condicion: String,
    val temperatura: Double,
    val vientoVelocidad: Double,
    val humedad: Int,
    val iconoUrl: String
)
