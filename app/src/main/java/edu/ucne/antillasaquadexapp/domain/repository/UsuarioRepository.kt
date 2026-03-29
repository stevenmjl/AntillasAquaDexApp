package edu.ucne.antillasaquadexapp.domain.repository

import edu.ucne.antillasaquadexapp.data.local.entities.UsuarioEntity
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {
    fun getUsuario(): Flow<UsuarioEntity?>
    suspend fun saveUsuario(nombre: String)
}
