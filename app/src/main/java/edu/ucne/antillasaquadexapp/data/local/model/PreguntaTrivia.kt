package edu.ucne.antillasaquadexapp.data.local.model

data class PreguntaTrivia(
    val id: Int,
    val enunciado: String,
    val opciones: List<String>,
    val respuestaCorrecta: Int,
    val dificultad: Dificultad,
    val categoria: String,
    val especieId: Int? = null,
    val videoUrl: String? = null
)

enum class Dificultad {
    FACIL,
    DIFICIL
}
