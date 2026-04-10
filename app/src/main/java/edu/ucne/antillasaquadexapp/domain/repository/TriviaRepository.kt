package edu.ucne.antillasaquadexapp.domain.repository

import edu.ucne.antillasaquadexapp.data.local.entities.TriviaEntity
import edu.ucne.antillasaquadexapp.data.local.model.PreguntaTrivia
import kotlinx.coroutines.flow.Flow

interface TriviaRepository {
    fun getPreguntasPorCategoria(categoria: String): List<PreguntaTrivia>
    fun getProgresoPorCategoria(categoria: String): Flow<TriviaEntity?>
    suspend fun guardarProgreso(progreso: TriviaEntity)
    suspend fun actualizarVidas(categoria: String, vidas: Int)
    suspend fun registrarFallo(categoria: String, preguntaId: Int)
    suspend fun completarTrivia(categoria: String)
    fun getTodosLosProgresos(): Flow<List<TriviaEntity>>
}