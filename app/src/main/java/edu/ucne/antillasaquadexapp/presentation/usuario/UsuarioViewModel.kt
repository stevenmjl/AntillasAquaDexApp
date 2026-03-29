package edu.ucne.antillasaquadexapp.presentation.usuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.antillasaquadexapp.domain.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UsuarioUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            usuarioRepository.getUsuario().collect { usuario ->
                _state.update { it.copy(
                    nombre = usuario?.nombre ?: "",
                    esUsuarioGuardado = usuario != null,
                    isLoading = false
                ) }
            }
        }
    }

    fun onNombreChange(nuevoNombre: String) {
        _state.update { it.copy(nombre = nuevoNombre) }
    }

    fun guardarUsuario() {
        viewModelScope.launch {
            if (_state.value.nombre.isNotBlank()) {
                usuarioRepository.saveUsuario(_state.value.nombre)
            }
        }
    }
}
