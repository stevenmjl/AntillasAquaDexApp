package edu.ucne.antillasaquadexapp.data.remote.dto

import com.squareup.moshi.Json

data class EspecieDto(
    @Json(name = "especieId") val especieId: Int,
    @Json(name = "nombre") val nombre: String,
    @Json(name = "nombreCientifico") val nombreCientifico: String?,
    @Json(name = "grupo") val grupo: String,
    @Json(name = "longitudCm") val longitudCm: Double?,
    @Json(name = "estado") val estado: String?,
    @Json(name = "descripcion") val descripcion: String?,
    @Json(name = "habitat") val habitat: String?,
    @Json(name = "pesoKg") val pesoKg: Double?,
    @Json(name = "dieta") val dieta: String?,
    @Json(name = "infoReproduccion") val infoReproduccion: String?,
    @Json(name = "longevidad") val longevidad: String?,
    @Json(name = "imagenUrl") val imagenUrl: String?
)
