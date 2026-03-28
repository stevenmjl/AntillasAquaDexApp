package edu.ucne.antillasaquadexapp.data.repository

import edu.ucne.antillasaquadexapp.data.mappers.toDomain
import edu.ucne.antillasaquadexapp.data.remote.ClimaApi
import edu.ucne.antillasaquadexapp.domain.model.Clima
import edu.ucne.antillasaquadexapp.domain.repository.ClimaRepository
import edu.ucne.antillasaquadexapp.util.Resource
import javax.inject.Inject

class ClimaRepositoryImpl @Inject constructor(
    private val api: ClimaApi
) : ClimaRepository {
    override suspend fun getClima(lat: Double, lon: Double): Resource<Clima> {
        return try {
            val respuesta = api.getClima(lat, lon)
            Resource.Success(respuesta.toDomain())
        } catch (e: Exception) {
            Resource.Error("Error al obtener clima: ${e.localizedMessage}")
        }
    }
}
