package edu.ucne.antillasaquadexapp.data.local.dao

import androidx.room.*
import edu.ucne.antillasaquadexapp.data.local.entities.EspecieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EspecieDao {
    @Query("SELECT * FROM especies")
    fun getAll(): Flow<List<EspecieEntity>>

    @Query("SELECT * FROM especies WHERE especieId = :id")
    suspend fun getById(id: Int): EspecieEntity?

    @Upsert
    suspend fun upsertAll(especies: List<EspecieEntity>)

    @Upsert
    suspend fun upsert(especie: EspecieEntity)
}
