package edu.ucne.antillasaquadexapp.domain.model

data class Pais(
    val nombre: String,
    val zonas: List<Zona>
)

data class Zona(
    val nombre: String,
    val descripcion: String,
    val imagenUrl: String,
    val latitud: Double,
    val longitud: Double,
    val especieIds: List<Int>
)

val Paises = listOf(
    Pais(
        nombre = "República Dominicana",
        zonas = listOf(
            Zona("Bahía de Samaná", "Hogar de las ballenas jorobadas.", "https://example.com/samana.jpg", 19.2, -69.3, listOf(1, 4)),
            Zona("Parque Nacional Jaragua", "Gran biodiversidad marina.", "https://example.com/jaragua.jpg", 17.8, -71.5, listOf(2, 3))
        )
    ),
    Pais(
        nombre = "Cuba",
        zonas = listOf(
            Zona("Jardines de la Reina", "Arrecifes de coral vírgenes.", "https://example.com/jardines.jpg", 20.8, -78.9, listOf(1, 2, 3)),
            Zona("Bahía de Cochinos", "Aguas cristalinas y buceo.", "https://example.com/cochinos.jpg", 22.1, -81.1, listOf(3, 4))
        )
    ),
    Pais(
        nombre = "Jamaica",
        zonas = listOf(
            Zona("Montego Bay Marine Park", "Protección de ecosistemas costeros.", "https://example.com/montego.jpg", 18.5, -77.9, listOf(1, 3)),
            Zona("Ocho Ríos", "Famoso por sus cascadas y arrecifes.", "https://example.com/ochorios.jpg", 18.4, -77.1, listOf(2, 4))
        )
    ),
    Pais(
        nombre = "Puerto Rico",
        zonas = listOf(
            Zona("Isla de Culebra", "Reserva natural con tortugas.", "https://example.com/culebra.jpg", 18.3, -65.3, listOf(3, 4)),
            Zona("La Parguera", "Bosques de manglares y bio-bahía.", "https://example.com/parguera.jpg", 17.9, -67.0, listOf(1, 2))
        )
    )
)
