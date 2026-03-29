package edu.ucne.antillasaquadexapp.presentation.favoritos

import edu.ucne.antillasaquadexapp.domain.model.Especie

data class FavoritosUiState(
    val isLoading: Boolean = false,
    val favoritos: List<Especie> = emptyList()
)