package edu.ucne.antillasaquadexapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.antillasaquadexapp.data.local.dao.FavoritoDao
import edu.ucne.antillasaquadexapp.data.local.dao.EspecieDao
import edu.ucne.antillasaquadexapp.data.local.dao.UsuarioDao
import edu.ucne.antillasaquadexapp.data.local.entities.FavoritoEntity
import edu.ucne.antillasaquadexapp.data.local.entities.EspecieEntity
import edu.ucne.antillasaquadexapp.data.local.entities.UsuarioEntity

@Database(
    entities = [
        UsuarioEntity::class,
        FavoritoEntity::class,
        EspecieEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun favoritoDao(): FavoritoDao
    abstract fun especieDao(): EspecieDao
}