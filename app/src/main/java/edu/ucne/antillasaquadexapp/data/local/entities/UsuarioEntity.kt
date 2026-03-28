package edu.ucne.antillasaquadexapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey val usuarioId: Int = 1,
    val nombre: String
)
