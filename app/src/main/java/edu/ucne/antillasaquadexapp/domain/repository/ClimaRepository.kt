package edu.ucne.antillasaquadexapp.domain.repository

import edu.ucne.antillasaquadexapp.domain.model.Clima
import edu.ucne.antillasaquadexapp.util.Resource

interface ClimaRepository {
    suspend fun getClima(lat: Double, lon: Double): Resource<Clima>
}
