package edu.ucne.antillasaquadexapp.presentation.usuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.antillasaquadexapp.domain.repository.UsuarioRepository
import edu.ucne.antillasaquadexapp.util.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository,
    private val preferencesManager: PreferencesManager
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

        viewModelScope.launch {
            preferencesManager.musicVolume.collectLatest { volume ->
                _state.update { it.copy(volumen = volume) }
            }
        }

        viewModelScope.launch {
            preferencesManager.profilePictureUrl.collectLatest { url ->
                _state.update { it.copy(profilePictureUrl = url) }
            }
        }
    }

    fun onNombreChange(nuevoNombre: String) {
        _state.update { it.copy(nombre = nuevoNombre) }
    }

    fun toggleEditingName() {
        _state.update { it.copy(isEditingName = !it.isEditingName) }
    }

    fun onVolumenChange(nuevoVolumen: Float) {
        viewModelScope.launch {
            preferencesManager.setMusicVolume(nuevoVolumen)
        }
    }

    fun setProfilePicture(url: String) {
        viewModelScope.launch {
            preferencesManager.setProfilePictureUrl(url)
        }
    }

    fun guardarUsuario() {
        viewModelScope.launch {
            if (_state.value.nombre.isNotBlank()) {
                usuarioRepository.saveUsuario(_state.value.nombre)
                _state.update { it.copy(isEditingName = false) }
            }
        }
    }
}
