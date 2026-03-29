package edu.ucne.antillasaquadexapp.data.mappers

import edu.ucne.antillasaquadexapp.data.local.entities.EspecieEntity
import edu.ucne.antillasaquadexapp.data.remote.dto.EspecieDto
import edu.ucne.antillasaquadexapp.domain.model.Especie

fun EspecieDto.toDomain() = Especie(
    especieId = especieId,
    nombre = nombre,
    nombreCientifico = nombreCientifico ?: "",
    grupo = grupo,
    esAnimal = esAnimal,
    estado = estado ?: "Desconocido",
    descripcion = descripcion ?: "",
    habitat = habitat ?: "",
    pesoKg = pesoKg ?: 0.0,
    dieta = dieta ?: "",
    infoReproduccion = infoReproduccion ?: "",
    longevidad = longevidad ?: "",
    imagenUrl = imagenUrl ?: ""
)

fun EspecieEntity.toDomain(esFavorito: Boolean = false) = Especie(
    especieId = especieId,
    nombre = nombre,
    nombreCientifico = nombreCientifico,
    grupo = grupo,
    esAnimal = esAnimal,
    estado = estado,
    descripcion = descripcion,
    habitat = habitat,
    pesoKg = pesoKg,
    dieta = dieta,
    infoReproduccion = infoReproduccion,
    longevidad = longevidad,
    imagenUrl = imagenUrl,
    esFavorito = esFavorito
)

fun Especie.toEntity() = EspecieEntity(
    especieId = especieId,
    nombre = nombre,
    nombreCientifico = nombreCientifico,
    grupo = grupo,
    esAnimal = esAnimal,
    estado = estado,
    descripcion = descripcion,
    habitat = habitat,
    pesoKg = pesoKg,
    dieta = dieta,
    infoReproduccion = infoReproduccion,
    longevidad = longevidad,
    imagenUrl = imagenUrl
)
