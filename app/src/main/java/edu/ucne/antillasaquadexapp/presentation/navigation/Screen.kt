package edu.ucne.antillasaquadexapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Bienvenida : Screen

    @Serializable
    data object Mapa : Screen

    @Serializable
    data class PaisDetalle(val paisNombre: String) : Screen

    @Serializable
    data class ZonaDetalle(
        val zonaNombre: String,
        val latitud: Double,
        val longitud: Double,
        val especieIds: List<Int>,
        val musicaResId: Int
    ) : Screen

    @Serializable
    data object EspecieLista : Screen

    @Serializable
    data object Trivia : Screen

    @Serializable
    data class EspecieDetalle(val especieId: Int) : Screen

    @Serializable
    data object Favoritos : Screen

    @Serializable
    data object Perfil : Screen
}
