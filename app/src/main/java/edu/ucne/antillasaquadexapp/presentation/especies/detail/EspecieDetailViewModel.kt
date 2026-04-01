package edu.ucne.antillasaquadexapp.presentation.especies.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.antillasaquadexapp.domain.repository.EspecieRepository
import edu.ucne.antillasaquadexapp.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EspecieDetailViewModel @Inject constructor(
    private val repository: EspecieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EspecieDetailUiState())
    val state = _state.asStateFlow()

    fun loadEspecie(id: Int) {
        viewModelScope.launch {
            // Solo mostramos loading si no tenemos ya la especie correcta cargada
            if (_state.value.especie?.especieId != id) {
                _state.update { it.copy(isLoading = true, error = null) }
            }
            
            when (val result = repository.getEspecieById(id)) {
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, especie = result.data, error = null) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
                else -> {}
            }
        }
    }

    fun toggleFavorito() {
        viewModelScope.launch {
            _state.value.especie?.let { especie ->
                repository.toggleFavorito(especie.especieId)
                val esFav = repository.esFavorito(especie.especieId)
                _state.update { it.copy(especie = it.especie?.copy(esFavorito = esFav)) }
            }
        }
    }
}
