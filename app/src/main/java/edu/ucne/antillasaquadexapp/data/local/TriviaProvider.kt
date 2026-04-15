package edu.ucne.antillasaquadexapp.data.local

import edu.ucne.antillasaquadexapp.data.local.model.Dificultad
import edu.ucne.antillasaquadexapp.data.local.model.PreguntaTrivia

object TriviaProvider {

    private val preguntasPeces = listOf(
        PreguntaTrivia(
            id = 1,
            enunciado = "¿Esta especie es un pez?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = 153
        )
    )

    private val preguntasMamiferos = listOf(
        PreguntaTrivia(
            id = 101,
            enunciado = "¿Los delfines son mamíferos?",
            opciones = listOf("Verdadero", "Falso"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "MAMÍFEROS"
        )
    )

    private val preguntasAves = listOf(
        PreguntaTrivia(
            id = 301,
            enunciado = "¿El Pelícano Pardo es un ave común en las Antillas?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "AVES"
        )
    )

    private val preguntasPlantas = listOf(
        PreguntaTrivia(
            id = 401,
            enunciado = "¿Los manglares protegen las costas de la erosión?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "PLANTAS"
        )
    )

    private val preguntasMoluscos = listOf(
        PreguntaTrivia(
            id = 501,
            enunciado = "¿El caracol reina es un molusco?",
            opciones = listOf("Verdadero", "Falso"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "MOLUSCOS"
        )
    )

    private val preguntasReptiles = listOf(
        PreguntaTrivia(
            id = 601,
            enunciado = "¿Las tortugas marinas ponen sus huevos en la arena?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "REPTILES"
        )
    )

    private val preguntasCnidarios = listOf(
        PreguntaTrivia(
            id = 701,
            enunciado = "¿Las medusas pertenecen al grupo de los Cnidarios?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "CNIDARIOS"
        )
    )

    private val preguntasPoriferos = listOf(
        PreguntaTrivia(
            id = 801,
            enunciado = "¿Las esponjas de mar son Poríferos?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "PORÍFEROS"
        )
    )

    private val preguntasCrustaceos = listOf(
        PreguntaTrivia(
            id = 901,
            enunciado = "¿Las langostas son crustáceos?",
            opciones = listOf("Verdadero", "Falso"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "CRUSTÁCEOS"
        )
    )

    private val preguntasEquinodermos = listOf(
        PreguntaTrivia(
            id = 1001,
            enunciado = "¿Las estrellas de mar son equinodermos?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 0,
            dificultad = Dificultad.FACIL,
            categoria = "EQUINODERMOS"
        )
    )

    fun getPreguntasPorCategoria(categoria: String): List<PreguntaTrivia> {
        return when (categoria.uppercase()) {
            "PECES" -> preguntasPeces
            "MAMÍFEROS" -> preguntasMamiferos
            "AVES" -> preguntasAves
            "PLANTAS" -> preguntasPlantas
            "MOLUSCOS" -> preguntasMoluscos
            "REPTILES" -> preguntasReptiles
            "CNIDARIOS" -> preguntasCnidarios
            "PORÍFEROS" -> preguntasPoriferos
            "CRUSTÁCEOS" -> preguntasCrustaceos
            "EQUINODERMOS" -> preguntasEquinodermos
            else -> emptyList()
        }
    }

    fun getAllPreguntas(): List<PreguntaTrivia> = 
        preguntasPeces + preguntasMamiferos + preguntasAves + preguntasPlantas + 
        preguntasMoluscos + preguntasReptiles + preguntasCnidarios + 
        preguntasPoriferos + preguntasCrustaceos + preguntasEquinodermos

    fun getCategoriasDisponibles(): List<String> = 
        listOf("PECES", "MAMÍFEROS", "AVES", "PLANTAS", "MOLUSCOS", "REPTILES", "CNIDARIOS", "PORÍFEROS", "CRUSTÁCEOS", "EQUINODERMOS")
}
