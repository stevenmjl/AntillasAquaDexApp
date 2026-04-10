package edu.ucne.antillasaquadexapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trivia_progreso")
data class TriviaEntity(
    @PrimaryKey
    val categoria: String, // Ejemplo: "PECES"
    val ultimaPreguntaId: Int = 1,
    val vidasRestantes: Int = 3,
    val mejorPuntaje: Int = 0,
    val falloEnPreguntaId: Int? = null,
    val medallaFacil: Boolean = false,
    val medallaDificil: Boolean = false,
    val perfectaSinPerderVidas: Boolean = false
)
