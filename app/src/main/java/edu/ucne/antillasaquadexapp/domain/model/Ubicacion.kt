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
    val imagenUrl: String,
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
                nombre = "Santuario de Ballenas, Bahía de Samaná",
                descripcion = "Cada invierno, estas majestuosas gigantes del océano transforman la bahía en un escenario de saltos y cantos ancestrales durante su temporada de apareamiento.",
                imagenUrl = "https://example.com/samana.jpg",
                latitud = 19.2,
                longitud = -69.3,
                especieIds = listOf(1, 4),
                musicaResId = R.raw.musica_samana
            ),
            Zona(
                nombre = "Reserva de la Biosfera Jaragua",
                descripcion = "Un santuario virgen donde los arrecifes de coral y las praderas de pastos marinos protegen a especies amenazadas en un paisaje de belleza árida y marina única.",
                imagenUrl = "https://example.com/jaragua.jpg",
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
                nombre = "Parque Nacional Marino Jardines de la Reina",
                descripcion = "El edén submarino mejor conservado del Caribe, rebosante de tiburones sedosos y corales gigantes que forman un ecosistema prácticamente virgen.",
                imagenUrl = "https://example.com/jardines.jpg",
                latitud = 20.8,
                longitud = -78.9,
                especieIds = listOf(1, 2, 3),
                musicaResId = R.raw.musica_jardines_reina
            ),
            Zona(
                nombre = "Reserva de la Biosfera Ciénaga de Zapata",
                descripcion = "El humedal más extenso del Caribe Insular; un laberinto mágico de manglares y canales donde la vida silvestre endémica encuentra su refugio ideal.",
                imagenUrl = "https://example.com/zapata.jpg",
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
                nombre = "Reserva Natural Isla de Mona",
                descripcion = "Conocida como 'las Galápagos del Caribe', esta meseta de piedra caliza alberga especies únicas y acantilados que esconden secretos de la historia marina.",
                imagenUrl = "https://example.com/mona.jpg",
                latitud = 18.1,
                longitud = -67.9,
                especieIds = listOf(3, 4),
                musicaResId = R.raw.musica_isla_mona
            ),
            Zona(
                nombre = "Bahía Bioluminiscente de Puerto Mosquito, Vieques",
                descripcion = "Considerada la más brillante del mundo, sus aguas se iluminan con destellos azul neón al menor movimiento, creando una galaxia submarina inolvidable.",
                imagenUrl = "https://example.com/vieques.jpg",
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
                nombre = "Montego Bay Marine Park",
                descripcion = "Un santuario de arrecifes recuperados donde la danza de los peces tropicales entre corales protegidos ofrece un espectáculo de biodiversidad recuperada.",
                imagenUrl = "https://example.com/montego.jpg",
                latitud = 18.5,
                longitud = -77.9,
                especieIds = listOf(1, 3),
                musicaResId = R.raw.musica_montego_bay
            ),
            Zona(
                nombre = "The Blue Lagoon, Port Antonio",
                descripcion = "Un abismo místico de aguas turquesas alimentado por manantiales subterráneos que se mezclan con el mar, rodeado de una selva esmeralda legendaria.",
                imagenUrl = "https://example.com/bluelagoon.jpg",
                latitud = 18.2,
                longitud = -76.4,
                especieIds = listOf(2, 4),
                musicaResId = R.raw.musica_blue_lagoon
            )
        )
    )
)
