package edu.ucne.antillasaquadexapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "especies")
data class EspecieEntity(
    @PrimaryKey val especieId: Int,
    val nombre: String,
    val nombreCientifico: String,
    val grupo: String,
    val longitudCm: Double,
    val estado: String,
    val descripcion: String,
    val habitat: String,
    val pesoKg: Double,
    val dieta: String,
    val infoReproduccion: String,
    val longevidad: String,
    val imagenUrl: String
)
