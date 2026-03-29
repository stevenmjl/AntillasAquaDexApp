package edu.ucne.antillasaquadexapp.presentation.clima

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.antillasaquadexapp.domain.repository.ClimaRepository
import edu.ucne.antillasaquadexapp.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClimaViewModel @Inject constructor(
    private val repository: ClimaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ClimaUiState())
    val state = _state.asStateFlow()

    fun loadClima(lat: Double, lon: Double) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = repository.getClima(lat, lon)) {
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, clima = result.data) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
                else -> {}
            }
        }
    }
}
