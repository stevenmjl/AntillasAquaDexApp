package edu.ucne.antillasaquadexapp.presentation.clima

import edu.ucne.antillasaquadexapp.domain.model.Clima

data class ClimaUiState(
    val isLoading: Boolean = false,
    val clima: Clima? = null,
    val error: String? = null
)