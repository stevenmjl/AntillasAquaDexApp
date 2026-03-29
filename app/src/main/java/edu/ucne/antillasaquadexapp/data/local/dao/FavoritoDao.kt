package edu.ucne.antillasaquadexapp.data.local.dao

import androidx.room.*
import edu.ucne.antillasaquadexapp.data.local.entities.FavoritoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritoDao {
    @Query("SELECT * FROM favoritos")
    fun getAll(): Flow<List<FavoritoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorito: FavoritoEntity)

    @Delete
    suspend fun delete(favorito: FavoritoEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favoritos WHERE especieId = :id)")
    suspend fun isFavorite(id: Int): Boolean
    
    @Query("SELECT COUNT(*) FROM favoritos")
    suspend fun getCount(): Int
}