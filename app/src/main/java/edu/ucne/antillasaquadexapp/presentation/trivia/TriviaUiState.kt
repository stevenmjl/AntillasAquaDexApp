package edu.ucne.antillasaquadexapp.presentation.trivia

import edu.ucne.antillasaquadexapp.data.local.model.PreguntaTrivia

data class TriviaUiState(
    val preguntas: List<PreguntaTrivia> = emptyList(),
    val preguntaActualIndex: Int = 0,
    val vidas: Int = 3,
    val tiempoRestante: Int = 30,
    val isGameOver: Boolean = false,
    val isVictory: Boolean = false,
    val isLoading: Boolean = true,
    val categoria: String = "PECES",
    val falloEnPreguntaId: Int? = null,
    val mostrarAyuda: Boolean = false,
    val mensajeRespuesta: String? = null,
    val esCorrecto: Boolean? = null,
    val imagenEspecieUrl: String? = null
)