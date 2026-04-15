package edu.ucne.antillasaquadexapp.data.local

import edu.ucne.antillasaquadexapp.data.local.model.Dificultad
import edu.ucne.antillasaquadexapp.data.local.model.PreguntaTrivia

object TriviaProvider {

    private val preguntasPeces = listOf(
        PreguntaTrivia(
            id = 1,
            enunciado = "¿Cuál es el pez más grande del mundo que habita en el Caribe?",
            opciones = listOf("Tiburón de Arrecife", "Mero Nasa", "Barracuda", "Tiburón Ballena"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = 22
        ),
        PreguntaTrivia(
            id = 2,
            enunciado = "El Pez León es conocido en el Caribe por ser una especie:",
            opciones = listOf("Protegida", "Endémica", "Invasora", "Extinta"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = 154
        ),
        PreguntaTrivia(
            id = 3,
            enunciado = "¿Qué pez es famoso por producir arena blanca al digerir el carbonato de calcio?",
            opciones = listOf("Barracuda", "Pez Loro", "Pez Cirujano", "Pez Cofre"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = 132
        ),
        PreguntaTrivia(
            id = 4,
            enunciado = "El Pez Erizo tiene la sorprendente capacidad de:",
            opciones = listOf("Cambiar de color", "Inflarse de agua o aire", "Volar distancias cortas", "Desaparecer en la arena"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = 155
        ),
        PreguntaTrivia(
            id = 5,
            enunciado = "¿Cuál de estos peces tiene una espina afilada como un bisturí en la base de su cola?",
            opciones = listOf("Ángel Reina", "Pargo Gris", "Rabirrubia", "Cirujano Azul"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = 139
        ),
        PreguntaTrivia(
            id = 6,
            enunciado = "El Tiburón Nodriza es un pez de fondo que suele ser más activo durante la:",
            opciones = listOf("Noche", "Mañana", "Tarde", "Migración anual"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = 12
        ),
        PreguntaTrivia(
            id = 7,
            enunciado = "¿De qué se alimenta principalmente el Tiburón Ballena?",
            opciones = listOf("Peces grandes", "Tortugas marinas", "Plancton y krill", "Corales"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "PECES",
            especieId = 22
        ),
        PreguntaTrivia(
            id = 8,
            enunciado = "El Grunt Francés emite sonidos característicos al:",
            opciones = listOf("Frotar sus dientes", "Golpear las rocas", "Mover sus aletas", "Soltar burbujas"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.DIFICIL,
            categoria = "PECES",
            especieId = 72
        ),
        PreguntaTrivia(
            id = 9,
            enunciado = "¿Cómo suele cazar el Pez Trompeta Atlántico en los arrecifes?",
            opciones = listOf("En grandes grupos", "Persiguiendo a gran velocidad", "Saltando del agua", "Sigilosamente oculto entre corales"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.DIFICIL,
            categoria = "PECES",
            especieId = 173
        ),
        PreguntaTrivia(
            id = 10,
            enunciado = "El Tiburón Martillo Gigante tiene un esqueleto compuesto por:",
            opciones = listOf("Hueso", "Quitina", "Cartílago", "Proteínas elásticas"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.DIFICIL,
            categoria = "PECES",
            especieId = 32
        )
    )

    private val preguntasMamiferos = listOf(
        PreguntaTrivia(
            id = 101,
            enunciado = "¿En qué zona de la República Dominicana es más común observar Ballenas Jorobadas en invierno?",
            opciones = listOf("Punta Cana", "Puerto Plata", "La Romana", "Bahía de Samaná"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "MAMÍFEROS",
            especieId = 1
        ),
        PreguntaTrivia(
            id = 102,
            enunciado = "Debido a su dieta de pastos marinos, al Manatí Antillano se le conoce como:",
            opciones = listOf("Elefante marino", "Perro de agua", "Vaca marina", "Cerdo de mar"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "MAMÍFEROS",
            especieId = 51
        ),
        PreguntaTrivia(
            id = 103,
            enunciado = "¿Cómo se comunican principalmente los delfines bajo el agua?",
            opciones = listOf("A través de luces", "Mediante sonidos y silbidos", "Cambiando de color", "Liberando burbujas"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "MAMÍFEROS",
            especieId = 21
        ),
        PreguntaTrivia(
            id = 104,
            enunciado = "¿Qué órgano del Cachalote es proporcionalmente el más grande de todo el reino animal?",
            opciones = listOf("Cerebro", "Corazón", "Estómago", "Hígado"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "MAMÍFEROS",
            especieId = 11
        ),
        PreguntaTrivia(
            id = 105,
            enunciado = "El Delfín Spinner es famoso en las Antillas por realizar saltos de tipo:",
            opciones = listOf("Hacia atrás", "Planos", "Verticales", "Giratorios"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "MAMÍFEROS",
            especieId = 61
        ),
        PreguntaTrivia(
            id = 106,
            enunciado = "El Manatí Antillano es un animal de dieta principalmente:",
            opciones = listOf("Carnívora", "Omnívora", "Herbívora", "Carroñera"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "MAMÍFEROS",
            especieId = 51
        ),
        PreguntaTrivia(
            id = 107,
            enunciado = "¿Los delfines respiran bajo el agua mediante branquias?",
            opciones = listOf("Verdadero", "Falso"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "MAMÍFEROS",
            especieId = 21
        ),
        PreguntaTrivia(
            id = 108,
            enunciado = "¿Qué récord ostenta el Zifio de Cuvier entre los mamíferos marinos?",
            opciones = listOf("Buceo más profundo", "Mayor velocidad de nado", "Canto más largo", "Mayor esperanza de vida"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.DIFICIL,
            categoria = "MAMÍFEROS",
            especieId = 111
        ),
        PreguntaTrivia(
            id = 109,
            enunciado = "El Murciélago Pescador Mayor, que habita en manglares, se alimenta de:",
            opciones = listOf("Solo frutas", "Solo néctar", "Sangre", "Peces e insectos"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.DIFICIL,
            categoria = "MAMÍFEROS",
            especieId = 179
        ),
        PreguntaTrivia(
            id = 110,
            enunciado = "Aunque se parece a una orca, la Falsa Orca es en realidad un miembro de la familia de los:",
            opciones = listOf("Tiburones", "Delfines", "Ballenas barbadas", "Manatíes"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.DIFICIL,
            categoria = "MAMÍFEROS",
            especieId = 151
        )
    )

    private val preguntasAves = listOf(
        PreguntaTrivia(
            id = 301,
            enunciado = "¿El Pelícano Pardo es un ave común en las Antillas?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "AVES"
        )
    )

    private val preguntasPlantas = listOf(
        PreguntaTrivia(
            id = 401,
            enunciado = "¿Los manglares protegen las costas de la erosión?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "PLANTAS"
        )
    )

    private val preguntasMoluscos = listOf(
        PreguntaTrivia(
            id = 501,
            enunciado = "¿El caracol reina es un molusco?",
            opciones = listOf("Verdadero", "Falso"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "MOLUSCOS"
        )
    )

    private val preguntasReptiles = listOf(
        PreguntaTrivia(
            id = 601,
            enunciado = "¿Las tortugas marinas ponen sus huevos en la arena?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "REPTILES"
        )
    )

    private val preguntasCnidarios = listOf(
        PreguntaTrivia(
            id = 701,
            enunciado = "¿Las medusas pertenecen al grupo de los Cnidarios?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "CNIDARIOS"
        )
    )

    private val preguntasPoriferos = listOf(
        PreguntaTrivia(
            id = 801,
            enunciado = "¿Las esponjas de mar son Poríferos?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "PORÍFEROS"
        )
    )

    private val preguntasCrustaceos = listOf(
        PreguntaTrivia(
            id = 901,
            enunciado = "¿Las langostas son crustáceos?",
            opciones = listOf("Verdadero", "Falso"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "CRUSTÁCEOS"
        )
    )

    private val preguntasEquinodermos = listOf(
        PreguntaTrivia(
            id = 1001,
            enunciado = "¿Las estrellas de mar son equinodermos?",
            opciones = listOf("Sí", "No"),
            respuestaCorrecta = 1,
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
