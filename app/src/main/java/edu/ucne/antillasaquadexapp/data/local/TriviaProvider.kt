package edu.ucne.antillasaquadexapp.data.local

import edu.ucne.antillasaquadexapp.data.local.model.Dificultad
import edu.ucne.antillasaquadexapp.data.local.model.PreguntaTrivia

object TriviaProvider {

    private val preguntasPeces = listOf(
        PreguntaTrivia(
            id = 1,
            enunciado = "¿Esta especie es un pez?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = 153
        ),
        PreguntaTrivia(
            id = 2,
            enunciado = "¿Cuánto mide aproximadamente la ballena jorobada?",
            opciones = listOf("12 metros", "14 metros", "25 metros", "16 metros"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = null,
            videoUrl = "https://youtu.be/WXkeDSTeT78?si=OeR1o5qMv_aLn2mF"
        ),
        PreguntaTrivia(
            id = 3,
            enunciado = "Los peces respiran a través de pulmones.",
            opciones = listOf("Verdadero", "Falso"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = null
        ),
        PreguntaTrivia(
            id = 4,
            enunciado = "¿Qué órgano permite a los peces flotar a diferentes profundidades?",
            opciones = listOf("Branquias", "Vejiga natatoria", "Aletas", "Hígado"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.DIFICIL,
            categoria = "PECES",
            especieId = null
        )
    )

    fun getPreguntasPorCategoria(categoria: String): List<PreguntaTrivia> {
        return when (categoria.uppercase()) {
            "PECES" -> preguntasPeces
            else -> emptyList()
        }
    }

    fun getAllPreguntas(): List<PreguntaTrivia> = preguntasPeces // Expandir cuando haya más categorías
}
