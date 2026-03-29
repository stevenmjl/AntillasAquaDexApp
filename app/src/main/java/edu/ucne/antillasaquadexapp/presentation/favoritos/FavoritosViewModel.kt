package edu.ucne.antillasaquadexapp.presentation.favoritos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.antillasaquadexapp.domain.repository.EspecieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritosViewModel @Inject constructor(
    private val repository: EspecieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FavoritosUiState())
    val state = _state.asStateFlow()

    init {
        loadFavoritos()
    }

    private fun loadFavoritos() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getFavoritos().collect { list ->
                _state.update { it.copy(isLoading = false, favoritos = list) }
            }
        }
    }

    fun removeFavorite(especieId: Int) {
        viewModelScope.launch {
            repository.toggleFavorito(especieId)
        }
    }
}
