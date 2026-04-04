package edu.ucne.antillasaquadexapp.presentation.usuario

data class UsuarioUiState(
    val nombre: String = "",
    val esUsuarioGuardado: Boolean = false,
    val isLoading: Boolean = true,
    val volumen: Float = 0.7f
)