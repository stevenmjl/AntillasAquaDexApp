package edu.ucne.antillasaquadexapp.presentation.titulo

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
class TituloViewModel @Inject constructor(
    private val especieRepository: EspecieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TituloUiState())
    val state = _state.asStateFlow()

    init {
        revisarSyncStatus()
    }

    private fun revisarSyncStatus() {
        viewModelScope.launch {
            especieRepository.esSyncCompletada().collect { isCompleted ->
                if (isCompleted) {
                    _state.update { it.copy(isLoading = false, isCompletado = true) }
                } else {
                    sincronizar()
                }
            }
        }
    }

    fun sincronizar() {
        viewModelScope.launch {
            especieRepository.sincronizarEspecies().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(
                            isLoading = true, 
                            error = null,
                            progreso = result.data ?: _state.value.progreso
                        ) }
                    }
                    is Resource.Success -> {
                        _state.update { it.copy(isLoading = false, isCompletado = true, progreso = 100) }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, error = result.message) }
                    }
                }
            }
        }
    }
}