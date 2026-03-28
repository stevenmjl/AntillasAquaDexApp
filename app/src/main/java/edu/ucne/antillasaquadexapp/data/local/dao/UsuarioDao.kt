package edu.ucne.antillasaquadexapp.data.local.dao

import androidx.room.*
import edu.ucne.antillasaquadexapp.data.local.entities.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios WHERE usuarioId = 1")
    fun getUsuario(): Flow<UsuarioEntity?>

    @Upsert
    suspend fun upsert(usuario: UsuarioEntity)
}
