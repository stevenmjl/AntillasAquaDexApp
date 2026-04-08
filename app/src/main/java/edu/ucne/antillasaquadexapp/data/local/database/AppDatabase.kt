package edu.ucne.antillasaquadexapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.antillasaquadexapp.data.local.dao.FavoritoDao
import edu.ucne.antillasaquadexapp.data.local.dao.EspecieDao
import edu.ucne.antillasaquadexapp.data.local.dao.UsuarioDao
import edu.ucne.antillasaquadexapp.data.local.dao.TriviaDao
import edu.ucne.antillasaquadexapp.data.local.entities.FavoritoEntity
import edu.ucne.antillasaquadexapp.data.local.entities.EspecieEntity
import edu.ucne.antillasaquadexapp.data.local.entities.UsuarioEntity
import edu.ucne.antillasaquadexapp.data.local.entities.TriviaEntity

@Database(
    entities = [
        UsuarioEntity::class,
        FavoritoEntity::class,
        EspecieEntity::class,
        TriviaEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun favoritoDao(): FavoritoDao
    abstract fun especieDao(): EspecieDao
    abstract fun triviaDao(): TriviaDao
}
