package edu.ucne.antillasaquadexapp.data.repository

import edu.ucne.antillasaquadexapp.data.local.TriviaProvider
import edu.ucne.antillasaquadexapp.data.local.dao.TriviaDao
import edu.ucne.antillasaquadexapp.data.local.entities.TriviaEntity
import edu.ucne.antillasaquadexapp.data.local.model.PreguntaTrivia
import edu.ucne.antillasaquadexapp.domain.repository.TriviaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TriviaRepositoryImpl @Inject constructor(
    private val triviaDao: TriviaDao
) : TriviaRepository {

    override fun getPreguntasPorCategoria(categoria: String): List<PreguntaTrivia> {
        return TriviaProvider.getPreguntasPorCategoria(categoria)
    }

    override fun getProgresoPorCategoria(categoria: String): Flow<TriviaEntity?> {
        return triviaDao.getProgresoPorCategoria(categoria)
    }

    override suspend fun guardarProgreso(progreso: TriviaEntity) {
        triviaDao.saveProgreso(progreso)
    }

    override suspend fun actualizarVidas(categoria: String, vidas: Int) {
        triviaDao.updateVidas(categoria, vidas)
    }

    override suspend fun registrarFallo(categoria: String, preguntaId: Int) {
        triviaDao.registrarFallo(categoria, preguntaId)
    }

    override suspend fun completarTrivia(categoria: String) {
        triviaDao.completarTrivia(categoria)
    }

    override fun getTodosLosProgresos(): Flow<List<TriviaEntity>> {
        return triviaDao.getAllProgreso()
    }
}