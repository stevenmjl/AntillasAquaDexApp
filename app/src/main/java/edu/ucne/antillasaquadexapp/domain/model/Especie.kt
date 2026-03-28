package edu.ucne.antillasaquadexapp.domain.model

data class Especie(
    val especieId: Int,
    val nombre: String,
    val nombreCientifico: String,
    val grupo: String,
    val esAnimal: Boolean,
    val estado: String,
    val descripcion: String,
    val habitat: String,
    val pesoKg: Double,
    val dieta: String,
    val infoReproduccion: String,
    val longevidad: String,
    val imagenUrl: String,
    val esFavorito: Boolean = false
)
