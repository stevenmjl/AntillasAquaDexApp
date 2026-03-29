package edu.ucne.antillasaquadexapp.data.repository

import edu.ucne.antillasaquadexapp.data.local.dao.FavoritoDao
import edu.ucne.antillasaquadexapp.data.local.dao.EspecieDao
import edu.ucne.antillasaquadexapp.data.local.entities.FavoritoEntity
import edu.ucne.antillasaquadexapp.data.mappers.toDomain
import edu.ucne.antillasaquadexapp.data.mappers.toEntity
import edu.ucne.antillasaquadexapp.data.remote.EspecieApi
import edu.ucne.antillasaquadexapp.domain.model.Especie
import edu.ucne.antillasaquadexapp.domain.repository.EspecieRepository
import edu.ucne.antillasaquadexapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class EspecieRepositoryImpl @Inject constructor(
    private val api: EspecieApi,
    private val favoritoDao: FavoritoDao,
    private val especieDao: EspecieDao
) : EspecieRepository {

    override suspend fun getEspecies(page: Int): Resource<List<Especie>> {
        return try {
            val remoteEspecies = api.getEspecies(page)
            val entities = remoteEspecies.map { it.toDomain().toEntity() }
            especieDao.upsertAll(entities)
            
            val favoritos = favoritoDao.getAll().first().map { it.especieId }.toSet()
            val especieList = entities.map { it.toDomain(favoritos.contains(it.especieId)) }
            Resource.Success(especieList)
        } catch (e: Exception) {
            val localEntities = especieDao.getAll().first()
            if (localEntities.isNotEmpty()) {
                val favorites = favoritoDao.getAll().first().map { it.especieId }.toSet()
                Resource.Success(localEntities.map { it.toDomain(favorites.contains(it.especieId)) })
            } else {
                Resource.Error("Error de conexión: ${e.localizedMessage}")
            }
        }
    }

    override suspend fun getEspecieById(id: Int): Resource<Especie> {
        return try {
            val respuesta = api.getEspecieById(id)
            if (respuesta.isSuccessful && respuesta.body() != null) {
                val especie = respuesta.body()!!.toDomain()
                especieDao.upsert(especie.toEntity())
                val isFav = favoritoDao.isFavorite(id)
                Resource.Success(especie.copy(esFavorito = isFav))
            } else {
                val local = especieDao.getById(id)
                if (local != null) {
                    val isFav = favoritoDao.isFavorite(id)
                    Resource.Success(local.toDomain(isFav))
                } else {
                    Resource.Error("Especie no encontrada")
                }
            }
        } catch (e: Exception) {
            val local = especieDao.getById(id)
            if (local != null) {
                val isFav = favoritoDao.isFavorite(id)
                Resource.Success(local.toDomain(isFav))
            } else {
                Resource.Error("Error: ${e.message}")
            }
        }
    }

    override fun getFavoritos(): Flow<List<Especie>> {
        return combine(
            favoritoDao.getAll(),
            especieDao.getAll()
        ) { favorites, allSpecies ->
            val favIds = favorites.map { it.especieId }.toSet()
            allSpecies.filter { favIds.contains(it.especieId) }
                .map { it.toDomain(true) }
        }
    }

    override suspend fun toggleFavorito(especieId: Int) {
        if (favoritoDao.isFavorite(especieId)) {
            favoritoDao.delete(FavoritoEntity(especieId))
        } else {
            if (favoritoDao.getCount() < 20) {
                favoritoDao.insert(FavoritoEntity(especieId))
            }
        }
    }

    override suspend fun esFavorito(especieId: Int): Boolean {
        return favoritoDao.isFavorite(especieId)
    }
}
