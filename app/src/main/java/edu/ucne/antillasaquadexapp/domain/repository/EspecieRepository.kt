package edu.ucne.antillasaquadexapp.domain.repository

import edu.ucne.antillasaquadexapp.domain.model.Especie
import edu.ucne.antillasaquadexapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface EspecieRepository {
    suspend fun getEspecies(page: Int = 1): Resource<List<Especie>>
    suspend fun getEspecieById(id: Int): Resource<Especie>
    fun getFavoritos(): Flow<List<Especie>>
    suspend fun toggleFavorito(especieId: Int)
    suspend fun esFavorito(especieId: Int): Boolean
    suspend fun sincronizarEspecies(): Flow<Resource<Int>>
    fun esSyncCompletada(): Flow<Boolean>
}
