package edu.ucne.antillasaquadexapp.presentation.especies.detail

import edu.ucne.antillasaquadexapp.domain.model.Especie

data class EspecieDetailUiState(
    val isLoading: Boolean = false,
    val especie: Especie? = null,
    val error: String? = null,
    val mensajeFavorito: String? = null
)