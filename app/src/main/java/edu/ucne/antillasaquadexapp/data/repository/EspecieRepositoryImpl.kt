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
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
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
            // Intentamos obtener el total real si es posible, o usamos un estimado para la barra de progreso
            val totalPaginasEstimadas = 5 
            
            while (hayMasEspecies) {
                var attempt = 0
                var success = false
                var lastError = ""

                while (attempt < maxRetries && !success) {
                    try {
                        val remoteEspecies = api.getEspecies(page)
                        if (remoteEspecies.isNotEmpty()) {
                            val entities = remoteEspecies.map { it.toDomain().toEntity() }
                            especieDao.upsertAll(entities)
                            
                            // Precarga de imágenes REAL (espera a que descarguen en paralelo)
                            coroutineScope {
                                remoteEspecies.map { dto ->
                                    async {
                                        val request = ImageRequest.Builder(context)
                                            .data(dto.imagenUrl ?: "")
                                            .build()
                                        imageLoader.execute(request)
                                    }
                                }.awaitAll()
                            }

                            val progreso = if (page <= totalPaginasEstimadas) 
                                (page * 100) / totalPaginasEstimadas 
                            else 99
                            
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
                            delay(5000)
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

    override suspend fun sincronizarNuevasEspecies() {
        try {
            var page = 1
            var hayMasEspecies = true
            
            while (hayMasEspecies) {
                try {
                    val remoteEspecies = api.getEspecies(page)
                    if (remoteEspecies.isNotEmpty()) {
                        val entities = remoteEspecies.map { it.toDomain().toEntity() }
                        especieDao.upsertAll(entities)
                        
                        // Precarga silenciosa de imágenes
                        coroutineScope {
                            remoteEspecies.map { dto ->
                                async {
                                    val request = ImageRequest.Builder(context)
                                        .data(dto.imagenUrl ?: "")
                                        .build()
                                    imageLoader.execute(request)
                                }
                            }.awaitAll()
                        }
                        page++
                    } else {
                        hayMasEspecies = false
                    }
                } catch (e: Exception) {
                    // En segundo plano fallamos silenciosamente o registramos el error
                    hayMasEspecies = false
                }
            }
        } catch (e: Exception) {
            // Error general en segundo plano ignorado para no molestar al usuario
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
            val favMap = favorites.associateBy { it.especieId }
            allSpecies.filter { favMap.containsKey(it.especieId) }
                .map { it.toDomain(true) }
                .sortedBy { favMap[it.especieId]?.orden ?: 0 }
        }
    }

    override suspend fun toggleFavorito(especieId: Int): ToggleResultado {
        return if (favoritoDao.isFavorite(especieId)) {
            favoritoDao.delete(FavoritoEntity(especieId))
            ToggleResultado.Removido
        } else {
            val count = favoritoDao.getCount()
            if (count < 20) {
                val maxOrden = favoritoDao.getMaxOrden() ?: -1
                favoritoDao.insert(FavoritoEntity(especieId, maxOrden + 1))
                ToggleResultado.Agregado(count + 1)
            } else {
                ToggleResultado.LimiteAlcanzado
            }
        }
    }

    override suspend fun reordenarFavoritos(nuevaLista: List<Especie>) {
        val entities = nuevaLista.mapIndexed { index, especie ->
            FavoritoEntity(especie.especieId, index)
        }
        favoritoDao.updateAll(entities)
    }

    override suspend fun getFavoritosCount(): Int {
        return favoritoDao.getCount()
    }

    override suspend fun esFavorito(especieId: Int): Boolean {
        return favoritoDao.isFavorite(especieId)
    }
}
