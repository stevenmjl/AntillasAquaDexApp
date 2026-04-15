package edu.ucne.antillasaquadexapp.presentation.usuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.antillasaquadexapp.R
import edu.ucne.antillasaquadexapp.domain.repository.TriviaRepository
import edu.ucne.antillasaquadexapp.domain.repository.UsuarioRepository
import edu.ucne.antillasaquadexapp.util.PreferencesManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository,
    private val triviaRepository: TriviaRepository,
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

        viewModelScope.launch {
            triviaRepository.getTodosLosProgresos().collectLatest { progresos ->
                val medallas = listOf(
                    MedallaInfo(
                        categoria = "PECES",
                        conseguida = progresos.find { it.categoria == "PECES" }?.medallaFacil ?: false,
                        imagenResId = R.drawable.img_medalla_peces,
                        descripcion = "Completar Trivia Peces"
                    ),
                    MedallaInfo(
                        categoria = "MAMÍFEROS",
                        conseguida = progresos.find { it.categoria == "MAMÍFEROS" }?.medallaFacil ?: false,
                        imagenResId = R.drawable.img_medalla_mamiferos,
                        descripcion = "Completar Trivia Mamíferos"
                    ),
                    MedallaInfo(
                        categoria = "AVES",
                        conseguida = progresos.find { it.categoria == "AVES" }?.medallaFacil ?: false,
                        imagenResId = R.drawable.img_medalla_aves,
                        descripcion = "Completar Trivia Aves"
                    ),
                    MedallaInfo(
                        categoria = "PLANTAS",
                        conseguida = progresos.find { it.categoria == "PLANTAS" }?.medallaFacil ?: false,
                        imagenResId = R.drawable.img_medalla_plantas,
                        descripcion = "Completar Trivia Plantas"
                    ),
                    MedallaInfo(
                        categoria = "MOLUSCOS",
                        conseguida = progresos.find { it.categoria == "MOLUSCOS" }?.medallaFacil ?: false,
                        imagenResId = R.drawable.img_medalla_moluscos,
                        descripcion = "Completar Trivia Moluscos"
                    ),
                    MedallaInfo(
                        categoria = "REPTILES",
                        conseguida = progresos.find { it.categoria == "REPTILES" }?.medallaFacil ?: false,
                        imagenResId = R.drawable.img_medalla_reptiles,
                        descripcion = "Completar Trivia Reptiles"
                    ),
                    MedallaInfo(
                        categoria = "CNIDARIOS",
                        conseguida = progresos.find { it.categoria == "CNIDARIOS" }?.medallaFacil ?: false,
                        imagenResId = R.drawable.img_medalla_cnidarios,
                        descripcion = "Completar Trivia Cnidarios"
                    ),
                    MedallaInfo(
                        categoria = "PORÍFEROS",
                        conseguida = progresos.find { it.categoria == "PORÍFEROS" }?.medallaFacil ?: false,
                        imagenResId = R.drawable.img_medalla_poliferos,
                        descripcion = "Completar Trivia Poríferos"
                    ),
                    MedallaInfo(
                        categoria = "CRUSTÁCEOS",
                        conseguida = progresos.find { it.categoria == "CRUSTÁCEOS" }?.medallaFacil ?: false,
                        imagenResId = R.drawable.img_medalla_crustaceos,
                        descripcion = "Completar Trivia Crustáceos"
                    ),
                    MedallaInfo(
                        categoria = "EQUINODERMOS",
                        conseguida = progresos.find { it.categoria == "EQUINODERMOS" }?.medallaFacil ?: false,
                        imagenResId = R.drawable.img_medalla_equinodermos,
                        descripcion = "Completar Trivia Equinodermos"
                    )
                )
                _state.update { it.copy(medallas = medallas) }
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
