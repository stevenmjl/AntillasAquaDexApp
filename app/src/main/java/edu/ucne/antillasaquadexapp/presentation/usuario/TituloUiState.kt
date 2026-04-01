package edu.ucne.antillasaquadexapp.presentation.usuario

data class TituloUiState(
    val isLoading: Boolean = false,
    val isCompletado: Boolean = false,
    val error: String? = null
)