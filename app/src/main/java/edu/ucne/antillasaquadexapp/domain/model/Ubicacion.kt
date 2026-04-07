package edu.ucne.antillasaquadexapp.domain.model

import edu.ucne.antillasaquadexapp.R

data class Pais(
    val nombre: String,
    val imagenResId: Int,
    val zonas: List<Zona>
)

data class Zona(
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int,
    val latitud: Double,
    val longitud: Double,
    val especieIds: List<Int>,
    val musicaResId: Int
)

val Paises = listOf(
    Pais(
        nombre = "República Dominicana",
        imagenResId = R.drawable.img_repdom,
        zonas = listOf(
            Zona(
                nombre = "Bahía de Samaná",
                descripcion = "Santuario de ballenas jorobadas.",
                imagenResId = R.drawable.img_zona_samana,
                latitud = 19.2,
                longitud = -69.3,
                especieIds = listOf(1, 4),
                musicaResId = R.raw.musica_samana
            ),
            Zona(
                nombre = "Jaragua",
                descripcion = "Reserva de la biosfera.",
                imagenResId = R.drawable.img_zona_jaragua,
                latitud = 17.8,
                longitud = -71.5,
                especieIds = listOf(2, 3),
                musicaResId = R.raw.musica_jaragua
            )
        )
    ),
    Pais(
        nombre = "Cuba",
        imagenResId = R.drawable.img_cuba,
        zonas = listOf(
            Zona(
                nombre = "Jardines de la Reina",
                descripcion = "Parque nacional marino.",
                imagenResId = R.drawable.img_zona_jardines_reina,
                latitud = 20.8,
                longitud = -78.9,
                especieIds = listOf(1, 2, 3),
                musicaResId = R.raw.musica_jardines_reina
            ),
            Zona(
                nombre = "Ciénaga de Zapata",
                descripcion = "Reserva de la biosfera.",
                imagenResId = R.drawable.img_zona_cienaga_zapata,
                latitud = 22.3,
                longitud = -81.2,
                especieIds = listOf(3, 4),
                musicaResId = R.raw.musica_cienaga_zapata
            )
        )
    ),
    Pais(
        nombre = "Puerto Rico",
        imagenResId = R.drawable.img_puertorico,
        zonas = listOf(
            Zona(
                nombre = "Isla de Mona",
                descripcion = "Reserva natural.",
                imagenResId = R.drawable.img_zona_isla_mona,
                latitud = 18.1,
                longitud = -67.9,
                especieIds = listOf(3, 4),
                musicaResId = R.raw.musica_isla_mona
            ),
            Zona(
                nombre = "Vieques",
                descripcion = "Bahía bioluminiscente.",
                imagenResId = R.drawable.img_zona_vieques,
                latitud = 18.1,
                longitud = -65.4,
                especieIds = listOf(1, 2),
                musicaResId = R.raw.musica_bahia_vieques
            )
        )
    ),
    Pais(
        nombre = "Jamaica",
        imagenResId = R.drawable.img_jamaica,
        zonas = listOf(
            Zona(
                nombre = "Montego Bay",
                descripcion = "Parque marino.",
                imagenResId = R.drawable.img_zona_montego_bay,
                latitud = 18.5,
                longitud = -77.9,
                especieIds = listOf(1, 3),
                musicaResId = R.raw.musica_montego_bay
            ),
            Zona(
                nombre = "Blue Lagoon",
                descripcion = "Laguna azul.",
                imagenResId = R.drawable.img_zona_blue_lagoon,
                latitud = 18.2,
                longitud = -76.4,
                especieIds = listOf(2, 4),
                musicaResId = R.raw.musica_blue_lagoon
            )
        )
    )
)
