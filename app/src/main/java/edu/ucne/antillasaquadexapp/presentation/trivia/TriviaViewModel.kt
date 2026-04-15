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
            _state.update { 
                it.copy(
                    isLoading = true,
                    isVictory = false,
                    isGameOver = false,
                    preguntaActualIndex = 0,
                    vidas = 3,
                    esCorrecto = null,
                    mensajeRespuesta = null,
                    isPlaying = true
                )
            }
            
            val preguntas = triviaRepository.getPreguntasPorCategoria(categoria)
            
            _state.update { 
                it.copy(
                    preguntas = preguntas,
                    categoria = categoria,
                    isLoading = false
                )
            }
            if (preguntas.isNotEmpty()) {
                cargarImagenEspecie()
                startTimer()
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        val pregunta = _state.value.preguntas.getOrNull(_state.value.preguntaActualIndex)
        val tiempoInicial = if (pregunta?.dificultad == Dificultad.DIFICIL) 30 else 40
        _state.update { it.copy(tiempoRestante = tiempoInicial) }
        
        timerJob = viewModelScope.launch {
            while (_state.value.tiempoRestante > 0 && !_state.value.isGameOver && !_state.value.isVictory) {
                if (!_state.value.mostrarAyuda && !_state.value.mostrarConfirmacionSalir && _state.value.esCorrecto == null) {
                    delay(1000)
                    _state.update { it.copy(tiempoRestante = it.tiempoRestante - 1) }
                } else {
                    delay(500)
                }
            }
            if (_state.value.tiempoRestante <= 0 && _state.value.esCorrecto == null && !_state.value.isVictory) {
                perderVida()
            }
        }
    }

    fun responder(opcionSeleccionada: Int) {
        if (_state.value.esCorrecto != null || _state.value.isGameOver || _state.value.isVictory) return

        val indiceReal = opcionSeleccionada - 1
        val pregunta = _state.value.preguntas.getOrNull(_state.value.preguntaActualIndex) ?: return
        val esCorrecta = pregunta.respuestaCorrecta == indiceReal

        if (esCorrecta) {
            _state.update { it.copy(esCorrecto = true, mensajeRespuesta = "¡Correcto!") }
            viewModelScope.launch {
                delay(1200) 
                avanzarPregunta()
            }
        } else {
            _state.update { 
                it.copy(
                    esCorrecto = false, 
                    mensajeRespuesta = "Incorrecto. Era la opción ${pregunta.respuestaCorrecta + 1}" 
                ) 
            }
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
            // SOLO AQUÍ SE GANA: Cuando se responde bien la última pregunta
            timerJob?.cancel()
            _state.update { it.copy(isVictory = true, esCorrecto = null) }
            viewModelScope.launch {
                triviaRepository.completarTrivia(_state.value.categoria)
            }
        }
    }

    private fun perderVida() {
        val nuevasVidas = _state.value.vidas - 1
        if (nuevasVidas <= 0) {
            timerJob?.cancel()
            val preguntaId = _state.value.preguntas.getOrNull(_state.value.preguntaActualIndex)?.id ?: 0
            _state.update { it.copy(vidas = 0, isGameOver = true, esCorrecto = null) }
            viewModelScope.launch {
                triviaRepository.registrarFallo(_state.value.categoria, preguntaId)
            }
        } else {
            // Si fallas, NO avanzamos. Reiniciamos el estado de la pregunta actual para re-intento.
            _state.update { 
                it.copy(
                    vidas = nuevasVidas, 
                    esCorrecto = null, 
                    mensajeRespuesta = null
                ) 
            }
            // Reiniciamos el temporizador para que el usuario tenga tiempo de pensar de nuevo
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

    fun toggleConfirmacionSalir() {
        _state.update { it.copy(mostrarConfirmacionSalir = !it.mostrarConfirmacionSalir) }
    }

    fun detenerTrivia() {
        timerJob?.cancel()
        _state.update { 
            it.copy(
                isPlaying = false, 
                isVictory = false, 
                isGameOver = false,
                preguntas = emptyList(), 
                mostrarConfirmacionSalir = false,
                esCorrecto = null,
                mensajeRespuesta = null
            ) 
        }
    }
}
