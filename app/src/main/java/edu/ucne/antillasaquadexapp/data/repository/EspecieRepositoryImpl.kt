package edu.ucne.antillasaquadexapp.data.repository

import edu.ucne.antillasaquadexapp.data.local.dao.FavoritoDao
import edu.ucne.antillasaquadexapp.data.local.dao.EspecieDao
import edu.ucne.antillasaquadexapp.data.local.entities.FavoritoEntity
import edu.ucne.antillasaquadexapp.data.mappers.toDomain
import edu.ucne.antillasaquadexapp.data.mappers.toEntity
import edu.ucne.antillasaquadexapp.data.remote.EspecieApi
import edu.ucne.antillasaquadexapp.domain.model.Especie
import edu.ucne.antillasaquadexapp.domain.repository.EspecieRepository
import edu.ucne.antillasaquadexapp.domain.repository.ToggleResultado
import edu.ucne.antillasaquadexapp.util.PreferencesManager
import edu.ucne.antillasaquadexapp.util.Resource
import android.content.Context
import coil.ImageLoader
import coil.request.ImageRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class EspecieRepositoryImpl @Inject constructor(
    private val api: EspecieApi,
    private val favoritoDao: FavoritoDao,
    private val especieDao: EspecieDao,
    private val preferencesManager: PreferencesManager,
    private val imageLoader: ImageLoader,
    @ApplicationContext private val context: Context
) : EspecieRepository {

    override fun esSyncCompletada(): Flow<Boolean> = preferencesManager.isInitialSyncCompleted

    override suspend fun sincronizarEspecies(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading(0))
        try {
            var page = 1
            var hayMasEspecies = true
            val maxRetries = 10
            val totalPaginas = 4 
            
            while (hayMasEspecies && page <= totalPaginas) {
                var attempt = 0
                var success = false
                var lastError = ""

                while (attempt < maxRetries && !success) {
                    try {
                        val remoteEspecies = api.getEspecies(page)
                        if (remoteEspecies.isNotEmpty()) {
                            val entities = remoteEspecies.map { it.toDomain().toEntity() }
                            especieDao.upsertAll(entities)
                            
                            // Precarga de imágenes
                            remoteEspecies.forEach { dto ->
                                val request = ImageRequest.Builder(context)
                                    .data(dto.imagenUrl ?: "")
                                    .build()
                                imageLoader.enqueue(request)
                            }

                            val progreso = (page * 100) / totalPaginas
                            emit(Resource.Loading(progreso))
                            page++
                        } else {
                            hayMasEspecies = false
                        }
                        success = true
                    } catch (e: Exception) {
                        attempt++
                        lastError = e.localizedMessage ?: "Error desconocido"
                        if (attempt < maxRetries) {
                            delay(5000) // Esperar 5 segundos entre reintentos para dar tiempo a Azure
                        }
                    }
                }

                if (!success) {
                    throw Exception("Fallo persistente en página $page: $lastError")
                }
            }
            preferencesManager.setInitialSyncCompleted(true)
            emit(Resource.Success(100))
        } catch (e: Exception) {
            emit(Resource.Error("Error al sincronizar: ${e.localizedMessage}"))
        }
    }

    override suspend fun getEspecies(page: Int): Resource<List<Especie>> {
        val localEntities = especieDao.getAll().first()
        val favorites = favoritoDao.getAll().first().map { it.especieId }.toSet()
        return Resource.Success(localEntities.map { it.toDomain(favorites.contains(it.especieId)) })
    }

    override suspend fun getEspecieById(id: Int): Resource<Especie> {
        val local = especieDao.getById(id)
        return if (local != null) {
            val isFav = favoritoDao.isFavorite(id)
            Resource.Success(local.toDomain(isFav))
        } else {
            Resource.Error("Especie no encontrada localmente")
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

    override suspend fun toggleFavorito(especieId: Int): ToggleResultado {
        return if (favoritoDao.isFavorite(especieId)) {
            favoritoDao.delete(FavoritoEntity(especieId))
            ToggleResultado.Removido
        } else {
            val count = favoritoDao.getCount()
            if (count < 20) {
                favoritoDao.insert(FavoritoEntity(especieId))
                ToggleResultado.Agregado(count + 1)
            } else {
                ToggleResultado.LimiteAlcanzado
            }
        }
    }

    override suspend fun getFavoritosCount(): Int {
        return favoritoDao.getCount()
    }

    override suspend fun esFavorito(especieId: Int): Boolean {
        return favoritoDao.isFavorite(especieId)
    }
}
