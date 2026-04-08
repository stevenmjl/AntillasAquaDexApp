package edu.ucne.antillasaquadexapp.presentation.usuario

data class UsuarioUiState(
    val nombre: String = "",
    val esUsuarioGuardado: Boolean = false,
    val isLoading: Boolean = true,
    val volumen: Float = 0.7f,
    val isEditingName: Boolean = false,
    val profilePictureUrl: String? = null,
    val medallas: List<MedallaInfo> = emptyList()
)

data class MedallaInfo(
    val categoria: String,
    val conseguida: Boolean,
    val imagenResId: Int,
    val descripcion: String
)
