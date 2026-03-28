package edu.ucne.antillasaquadexapp.data.repository

import edu.ucne.antillasaquadexapp.data.local.dao.UsuarioDao
import edu.ucne.antillasaquadexapp.data.local.entities.UsuarioEntity
import edu.ucne.antillasaquadexapp.domain.repository.UsuarioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    private val usuarioDao: UsuarioDao
) : UsuarioRepository {
    override fun getUsuario(): Flow<UsuarioEntity?> = usuarioDao.getUsuario()

    override suspend fun saveUsuario(nombre: String) {
        usuarioDao.upsert(UsuarioEntity(nombre = nombre))
    }
}