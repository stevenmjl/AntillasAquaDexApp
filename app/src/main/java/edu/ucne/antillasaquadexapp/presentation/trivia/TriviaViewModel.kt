package edu.ucne.antillasaquadexapp.presentation.trivia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.antillasaquadexapp.data.local.model.Dificultad
import edu.ucne.antillasaquadexapp.domain.repository.TriviaRepository
import edu.ucne.antillasaquadexapp.domain.repository.EspecieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriviaViewModel @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val especieRepository: EspecieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TriviaUiState())
    val state: StateFlow<TriviaUiState> = _state.asStateFlow()

    private var timerJob: Job? = null

    fun iniciarTrivia(categoria: String) {
        viewModelScope.launch {
            val preguntas = triviaRepository.getPreguntasPorCategoria(categoria)
            val progreso = triviaRepository.getProgresoPorCategoria(categoria).firstOrNull()
            
            _state.update { 
                it.copy(
                    preguntas = preguntas,
                    categoria = categoria,
                    vidas = progreso?.vidasRestantes ?: 3,
                    preguntaActualIndex = 0,
                    isLoading = false
                )
            }
            cargarImagenEspecie()
            startTimer()
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        val tiempoInicial = if (_state.value.preguntas.getOrNull(_state.value.preguntaActualIndex)?.dificultad == Dificultad.DIFICIL) 10 else 20
        _state.update { it.copy(tiempoRestante = tiempoInicial) }
        
        timerJob = viewModelScope.launch {
            while (_state.value.tiempoRestante > 0 && !_state.value.isGameOver) {
                delay(1000)
                _state.update { it.copy(tiempoRestante = it.tiempoRestante - 1) }
            }
            if (_state.value.tiempoRestante == 0) {
                perderVida()
            }
        }
    }

    fun responder(indiceOpcion: Int) {
        val pregunta = _state.value.preguntas[_state.value.preguntaActualIndex]
        val esCorrecta = pregunta.respuestaCorrecta == indiceOpcion

        if (esCorrecta) {
            _state.update { it.copy(esCorrecto = true, mensajeRespuesta = "¡Correcto!") }
            viewModelScope.launch {
                delay(1500)
                avanzarPregunta()
            }
        } else {
            _state.update { it.copy(esCorrecto = false, mensajeRespuesta = "Incorrecto. Era: ${pregunta.opciones[pregunta.respuestaCorrecta]}") }
            viewModelScope.launch {
                delay(1500)
                perderVida()
            }
        }
    }

    private fun avanzarPregunta() {
        if (_state.value.preguntaActualIndex + 1 < _state.value.preguntas.size) {
            _state.update { 
                it.copy(
                    preguntaActualIndex = it.preguntaActualIndex + 1,
                    esCorrecto = null,
                    mensajeRespuesta = null
                ) 
            }
            cargarImagenEspecie()
            startTimer()
        } else {
            _state.update { it.copy(isVictory = true, esCorrecto = null) }
            // Aquí guardarías el logro de "Trivia completada"
        }
    }

    private fun perderVida() {
        val nuevasVidas = _state.value.vidas - 1
        if (nuevasVidas <= 0) {
            val preguntaId = _state.value.preguntas[_state.value.preguntaActualIndex].id
            _state.update { it.copy(vidas = 0, isGameOver = true, falloEnPreguntaId = preguntaId) }
            viewModelScope.launch {
                triviaRepository.registrarFallo(_state.value.categoria, preguntaId)
            }
        } else {
            _state.update { it.copy(vidas = nuevasVidas, esCorrecto = null, mensajeRespuesta = null) }
            startTimer()
        }
    }

    private fun cargarImagenEspecie() {
        val especieId = _state.value.preguntas.getOrNull(_state.value.preguntaActualIndex)?.especieId
        if (especieId != null) {
            viewModelScope.launch {
                val resource = especieRepository.getEspecieById(especieId)
                if (resource is edu.ucne.antillasaquadexapp.util.Resource.Success) {
                    _state.update { it.copy(imagenEspecieUrl = resource.data?.imagenUrl) }
                }
            }
        } else {
            _state.update { it.copy(imagenEspecieUrl = null) }
        }
    }

    fun toggleAyuda() {
        _state.update { it.copy(mostrarAyuda = !it.mostrarAyuda) }
    }
}
