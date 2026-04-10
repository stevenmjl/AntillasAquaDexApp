package edu.ucne.antillasaquadexapp.data.local.dao

import androidx.room.*
import edu.ucne.antillasaquadexapp.data.local.entities.TriviaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TriviaDao {

    @Query("SELECT * FROM trivia_progreso WHERE categoria = :categoria")
    fun getProgresoPorCategoria(categoria: String): Flow<TriviaEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgreso(progreso: TriviaEntity)

    @Query("UPDATE trivia_progreso SET ultimaPreguntaId = :preguntaId WHERE categoria = :categoria")
    suspend fun updateUltimaPregunta(categoria: String, preguntaId: Int)

    @Query("UPDATE trivia_progreso SET vidasRestantes = :vidas WHERE categoria = :categoria")
    suspend fun updateVidas(categoria: String, vidas: Int)

    @Query("UPDATE trivia_progreso SET falloEnPreguntaId = :preguntaId WHERE categoria = :categoria")
    suspend fun registrarFallo(categoria: String, preguntaId: Int)

    @Query("UPDATE trivia_progreso SET medallaFacil = 1 WHERE categoria = :categoria")
    suspend fun completarTrivia(categoria: String)
    
    @Query("SELECT * FROM trivia_progreso")
    fun getAllProgreso(): Flow<List<TriviaEntity>>
}
