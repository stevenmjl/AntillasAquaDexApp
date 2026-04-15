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
            opciones = listOf("Carnívora", "Omnívora", "Herbíbora", "Carroñera"),
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
            enunciado = "¿Qué ave marina del Caribe infla un saco gular rojo brillante para atraer a la hembra?",
            opciones = listOf("Pelícano Pardo", "Fragata Magnífica", "Piquero Café", "Gaviota Reidora"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "AVES",
            especieId = 53
        ),
        PreguntaTrivia(
            id = 302,
            enunciado = "El Pelícano Pardo es famoso por su técnica de pesca, la cual consiste en:",
            opciones = listOf("Nadar largas distancias", "Pescar de noche", "Lanzarse en picado desde el aire", "Robar comida a otros"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "AVES",
            especieId = 43
        ),
        PreguntaTrivia(
            id = 303,
            enunciado = "¿Cuál es el color característico de las patas del Piquero 'Sula sula'?",
            opciones = listOf("Rojo", "Azul", "Amarillo", "Negro"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "AVES",
            especieId = 73
        ),
        PreguntaTrivia(
            id = 304,
            enunciado = "La Gaviota Reidora recibe su nombre debido a:",
            opciones = listOf("Su plumaje colorido", "Su forma de caminar", "Su característico llamado similar a una risa", "Su nido circular"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "AVES",
            especieId = 83
        ),
        PreguntaTrivia(
            id = 305,
            enunciado = "¿Qué ave costera utiliza su largo pico rojo para abrir moluscos en las playas?",
            opciones = listOf("Garceta Nívea", "Ostrero Americano", "Playero Semipalmado", "Chorlo Gris"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "AVES",
            especieId = 143
        ),
        PreguntaTrivia(
            id = 306,
            enunciado = "El Rabijunco Etéreo es fácilmente reconocible en las Antillas por:",
            opciones = listOf("Sus patas rojas", "Su gran bolsa gular", "Su cola extremadamente larga", "Sus alas azules"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "AVES",
            especieId = 113
        ),
        PreguntaTrivia(
            id = 307,
            enunciado = "¿Cuántos huevos suele poner la Fragata Magnífica en cada temporada?",
            opciones = listOf("1 huevo", "3 huevos", "5 huevos", "10 huevos"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "AVES",
            especieId = 53
        ),
        PreguntaTrivia(
            id = 308,
            enunciado = "¿Cuál de estas aves se considera en 'Peligro' y anida en acantilados del Caribe?",
            opciones = listOf("Piquero Café", "Garza Real", "Petrel de las Antillas", "Charrán Bobo"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.DIFICIL,
            categoria = "AVES",
            especieId = 156
        ),
        PreguntaTrivia(
            id = 309,
            enunciado = "El Piquero Café puede llegar a vivir hasta los:",
            opciones = listOf("5 años", "10 años", "18 años", "40 años"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.DIFICIL,
            categoria = "AVES",
            especieId = 63
        ),
        PreguntaTrivia(
            id = 310,
            enunciado = "La Garza Real es un depredador paciente que caza principalmente en:",
            opciones = listOf("Alta mar", "Manglares y estuarios", "Bosques secos", "Montañas altas"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.DIFICIL,
            categoria = "AVES",
            especieId = 123
        )
    )

    private val preguntasPlantas = listOf(
        PreguntaTrivia(
            id = 401,
            enunciado = "¿Cuál es el principal pasto marino del Caribe que alimenta a tortugas y manatíes?",
            opciones = listOf("Hierba de Tortuga", "Sargazo", "Mangle Rojo", "Uva de Mar"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "PLANTAS",
            especieId = 9
        ),
        PreguntaTrivia(
            id = 402,
            enunciado = "¿Qué tipo de mangle tiene raíces aéreas en forma de arco?",
            opciones = listOf("Mangle Negro", "Mangle Rojo", "Mangle Blanco", "Mangle Botón"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "PLANTAS",
            especieId = 59
        ),
        PreguntaTrivia(
            id = 403,
            enunciado = "¿Qué alga parda forma grandes mantos flotantes en el mar abierto?",
            opciones = listOf("Uva de Mar", "Alga Pluma", "Sargazo", "Alga Roja"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "PLANTAS",
            especieId = 10
        ),
        PreguntaTrivia(
            id = 404,
            enunciado = "Alga que contribuye significativamente a la formación de arena blanca en el Caribe:",
            opciones = listOf("Alga Parda", "Alga Roja", "Alga Pincel", "Alga Calcárea"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "PLANTAS",
            especieId = 80
        ),
        PreguntaTrivia(
            id = 405,
            enunciado = "El Mangle Negro es conocido por tener raíces respiratorias llamadas:",
            opciones = listOf("Rizomas", "Neumatóforos", "Zarcillos", "Espinas"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "PLANTAS",
            especieId = 69
        ),
        PreguntaTrivia(
            id = 406,
            enunciado = "¿Qué alga verde tiene una forma característica de brocha o pincel?",
            opciones = listOf("Alga Pincel", "Uva de Mar", "Hierba de Sal", "Sargazo"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "PLANTAS",
            especieId = 100
        ),
        PreguntaTrivia(
            id = 407,
            enunciado = "Planta suculenta que crece en playas y ayuda a resistir la sequía:",
            opciones = listOf("Hierba de Mar", "Mangle Blanco", "Verdolaga de Mar", "Hierba de Bajío"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "PLANTAS",
            especieId = 109
        ),
        PreguntaTrivia(
            id = 408,
            enunciado = "¿Cómo se llama el pasto marino de hojas cilíndricas que es alimento del manatí?",
            opciones = listOf("Hierba de Tortuga", "Hierba de Bajío", "Hierba Paddle", "Hierba de Manatí"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.DIFICIL,
            categoria = "PLANTAS",
            especieId = 19
        ),
        PreguntaTrivia(
            id = 409,
            enunciado = "¿Qué especie de mangle tolera mejor los suelos secos y alejados del agua?",
            opciones = listOf("Mangle Botón", "Mangle Rojo", "Mangle Negro", "Mangle Blanco"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.DIFICIL,
            categoria = "PLANTAS",
            especieId = 89
        ),
        PreguntaTrivia(
            id = 410,
            enunciado = "La Uva de Mar (Caulerpa racemosa) recibe su nombre por:",
            opciones = listOf("Su sabor dulce", "Su color morado", "Sus estructuras en forma de racimo", "Su gran tamaño"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.DIFICIL,
            categoria = "PLANTAS",
            especieId = 60
        )
    )

    private val preguntasMoluscos = listOf(
        PreguntaTrivia(
            id = 501,
            enunciado = "Molusco icónico del Caribe con una concha rosada y gruesa:",
            opciones = listOf("Caracol Leche", "Caracol Reina", "Casco Emperador", "Burgao"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "MOLUSCOS",
            especieId = 25
        ),
        PreguntaTrivia(
            id = 502,
            enunciado = "El Pulpo Común es famoso por su capacidad de:",
            opciones = listOf("Volar distancias cortas", "Cantar", "Caminar erguido", "Cambiar color y textura"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "MOLUSCOS",
            especieId = 5
        ),
        PreguntaTrivia(
            id = 503,
            enunciado = "Molusco inteligente que cambia de color para comunicarse y cazar:",
            opciones = listOf("Calamar de Arrecife", "Ostra de Mangle", "Almeja Alada", "Liebre de Mar"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "MOLUSCOS",
            especieId = 15
        ),
        PreguntaTrivia(
            id = 504,
            enunciado = "¿Donde crece comúnmente la Ostra de Mangle?",
            opciones = listOf("En el fondo arenoso", "En mar abierto", "Adherida a raíces de mangle", "Dentro de cuevas"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "MOLUSCOS",
            especieId = 85
        ),
        PreguntaTrivia(
            id = 505,
            enunciado = "Molusco que libera una tinta morada cuando se siente amenazado:",
            opciones = listOf("Pulpo Común", "Liebre de Mar", "Nerita Sangrante", "Burgao"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "MOLUSCOS",
            especieId = 125
        ),
        PreguntaTrivia(
            id = 506,
            enunciado = "El Tritón de las Indias Occidentales es un importante depredador de:",
            opciones = listOf("Estrellas de mar", "Peces payaso", "Medusas", "Cangrejos"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "MOLUSCOS",
            especieId = 55
        ),
        PreguntaTrivia(
            id = 507,
            enunciado = "El Burgao es un caracol de concha oscura común en zonas:",
            opciones = listOf("Arenosas", "Profundas", "Fangosas", "Rocosas"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "MOLUSCOS",
            especieId = 65
        ),
        PreguntaTrivia(
            id = 508,
            enunciado = "Gran caracol que se alimenta principalmente de erizos de mar:",
            opciones = listOf("Caracol Reina", "Lengua de Flamenco", "Casco Emperador", "Ostra Perlífera"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.DIFICIL,
            categoria = "MOLUSCOS",
            especieId = 45
        ),
        PreguntaTrivia(
            id = 509,
            enunciado = "¿De qué se alimenta este pequeño y colorido caracol llamado Lengua de Flamenco?",
            opciones = listOf("Algas", "Gorgonias", "Detritos", "Peces"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.DIFICIL,
            categoria = "MOLUSCOS",
            especieId = 135
        ),
        PreguntaTrivia(
            id = 510,
            enunciado = "¿Cuál es la longevidad promedio de un Pulpo de Arrecife?",
            opciones = listOf("1 a 2 años", "10 a 15 años", "20 a 30 años", "50 años"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.DIFICIL,
            categoria = "MOLUSCOS",
            especieId = 159
        )
    )

    private val preguntasReptiles = listOf(
        PreguntaTrivia(
            id = 601,
            enunciado = "¿De qué se alimenta principalmente la Tortuga Verde adulta?",
            opciones = listOf("Medusas", "Esponjas", "Pastos marinos y algas", "Peces pequeños"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "REPTILES",
            especieId = 3
        ),
        PreguntaTrivia(
            id = 602,
            enunciado = "¿Cuál es el mayor reptil que habita en las Antillas?",
            opciones = listOf("Cocodrilo Americano", "Iguana de Ricordi", "Tortuga Laúd", "Jicotea Sureña"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "REPTILES",
            especieId = 181
        ),
        PreguntaTrivia(
            id = 603,
            enunciado = "La Tortuga Carey es conocida por su dieta basada en:",
            opciones = listOf("Crustáceos", "Algas", "Peces", "Esponjas"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "REPTILES",
            especieId = 13
        ),
        PreguntaTrivia(
            id = 604,
            enunciado = "¿Dónde es endémica la Iguana de Ricordi?",
            opciones = listOf("Puerto Rico", "Isla de la Hispaniola", "Jamaica", "Cuba"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "REPTILES",
            especieId = 183
        ),
        PreguntaTrivia(
            id = 605,
            enunciado = "La tortuga marina más grande del mundo es la:",
            opciones = listOf("Tortuga Verde", "Tortuga Carey", "Tortuga Caguama", "Tortuga Laúd"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "REPTILES",
            especieId = 33
        ),
        PreguntaTrivia(
            id = 606,
            enunciado = "Esta tortuga tiene una cabeza grande y mandíbulas poderosas para comer:",
            opciones = listOf("Tortuga Caguama", "Tortuga Verde", "Tortuga Carey", "Tortuga Laúd"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "REPTILES",
            especieId = 23
        ),
        PreguntaTrivia(
            id = 607,
            enunciado = "La Jicotea Sureña es una tortuga de:",
            opciones = listOf("Mar abierto", "Playas rocosas", "Agua dulce", "Zonas desérticas"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "REPTILES",
            especieId = 182
        ),
        PreguntaTrivia(
            id = 608,
            enunciado = "¿Cuántos huevos puede poner una Tortuga Verde por nidada?",
            opciones = listOf("10-20", "100-200", "500", "Más de 1000"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.DIFICIL,
            categoria = "REPTILES",
            especieId = 3
        ),
        PreguntaTrivia(
            id = 609,
            enunciado = "El Cocodrilo Americano puede vivir hasta:",
            opciones = listOf("20 años", "50 años", "70 años", "100 años"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.DIFICIL,
            categoria = "REPTILES",
            especieId = 181
        ),
        PreguntaTrivia(
            id = 610,
            enunciado = "El estado de conservación de la Tortuga Carey es:",
            opciones = listOf("En peligro crítico", "Vulnerable", "Extinto", "Preocupación menor"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.DIFICIL,
            categoria = "REPTILES",
            especieId = 13
        )
    )

    private val preguntasCnidarios = listOf(
        PreguntaTrivia(
            id = 701,
            enunciado = "Medusa translúcida común con cuatro estructuras circulares en su campana:",
            opciones = listOf("Medusa Luna", "Medusa Invertida", "Coral de Fuego", "Abanico de Mar"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "CNIDARIOS",
            especieId = 106
        ),
        PreguntaTrivia(
            id = 702,
            enunciado = "Coral fundamental para los arrecifes cuyas ramas parecen cuernos de mamífero:",
            opciones = listOf("Coral Cerebro", "Cuerno de Alce", "Coral Montaña", "Coral de Fuego"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "CNIDARIOS",
            especieId = 6
        ),
        PreguntaTrivia(
            id = 703,
            enunciado = "El Abanico de Mar (Gorgonia ventalina) se orienta según:",
            opciones = listOf("La luz solar", "El magnetismo", "Las corrientes de agua", "El viento"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "CNIDARIOS",
            especieId = 46
        ),
        PreguntaTrivia(
            id = 704,
            enunciado = "El Coral de Fuego recibe su nombre porque:",
            opciones = listOf("Brilla en la oscuridad", "Es de color rojo", "Vive en aguas calientes", "Causa una fuerte quemadura al tacto"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "CNIDARIOS",
            especieId = 86
        ),
        PreguntaTrivia(
            id = 705,
            enunciado = "Las anémonas gigantes proporcionan refugio a:",
            opciones = listOf("Tiburones", "Peces payaso y camarones", "Delfines", "Ballenas"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "CNIDARIOS",
            especieId = 66
        ),
        PreguntaTrivia(
            id = 706,
            enunciado = "Esta medusa vive boca abajo en el fondo para:",
            opciones = listOf("Dormir", "Esconderse de aves", "Hacer fotosíntesis con sus algas", "Poner huevos"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "CNIDARIOS",
            especieId = 96
        ),
        PreguntaTrivia(
            id = 707,
            enunciado = "El Coral Estrella Lobulado es muy longevo y puede vivir hasta:",
            opciones = listOf("300 años", "10 años", "50 años", "100 años"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.DIFICIL,
            categoria = "CNIDARIOS",
            especieId = 26
        ),
        PreguntaTrivia(
            id = 708,
            enunciado = "¿Qué defensa poseen las colonias de Zoántidos Caribeños?",
            opciones = listOf("Espinas", "Velocidad", "Camuflaje", "Toxinas potentes"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.DIFICIL,
            categoria = "CNIDARIOS",
            especieId = 116
        ),
        PreguntaTrivia(
            id = 709,
            enunciado = "El Coral Cuerno de Ciervo forma densos:",
            opciones = listOf("Bosques submarinos", "Círculos de arena", "Montones de rocas", "Túneles de lodo"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.DIFICIL,
            categoria = "CNIDARIOS",
            especieId = 16
        ),
        PreguntaTrivia(
            id = 710,
            enunciado = "Color característico de la gorgonia Plexaura homomalla (Abanico Negro):",
            opciones = listOf("Azul", "Negro o marrón oscuro", "Verde brillante", "Blanco"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.DIFICIL,
            categoria = "CNIDARIOS",
            especieId = 56
        )
    )

    private val preguntasPoriferos = listOf(
        PreguntaTrivia(
            id = 801,
            enunciado = "¿Cómo se alimentan los Poríferos o esponjas de mar?",
            opciones = listOf("Filtrando el agua", "Cazando peces", "Comiendo algas", "Fotosíntesis solamente"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "PORÍFEROS",
            especieId = 8
        ),
        PreguntaTrivia(
            id = 802,
            enunciado = "Esta esponja tiene la capacidad de perforar esqueletos de coral:",
            opciones = listOf("Esponja Barril", "Esponja Excavadora Roja", "Esponja Cerebro", "Esponja Tubo"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "PORÍFEROS",
            especieId = 78
        ),
        PreguntaTrivia(
            id = 803,
            enunciado = "Color característico de la Esponja Tubo Amarilla (Aplysina fistularis):",
            opciones = listOf("Rojo", "Verde", "Amarillo brillante", "Morado"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "PORÍFEROS",
            especieId = 38
        ),
        PreguntaTrivia(
            id = 804,
            enunciado = "Esponja masiva conocida como el 'Vaso del Mundo' por su forma:",
            opciones = listOf("Esponja Dedo", "Esponja Cerebro", "Esponja Hedionda", "Esponja Barril Gigante"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "PORÍFEROS",
            especieId = 58
        ),
        PreguntaTrivia(
            id = 805,
            enunciado = "La Esponja Cerebro recibe su nombre por su forma:",
            opciones = listOf("Lobulada", "Alargada", "Circular plana", "Cuadrada"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "PORÍFEROS",
            especieId = 18
        ),
        PreguntaTrivia(
            id = 806,
            enunciado = "Al igual que el coral de fuego, la Esponja de Fuego causa:",
            opciones = listOf("Sueño", "Hambre", "Frío", "Irritación al contacto"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "PORÍFEROS",
            especieId = 98
        ),
        PreguntaTrivia(
            id = 807,
            enunciado = "La textura de la Esponja Hígado de Pollo es descrita como:",
            opciones = listOf("Áspera", "Suave y carnosa", "Dura como roca", "Espinosa"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "PORÍFEROS",
            especieId = 138
        ),
        PreguntaTrivia(
            id = 808,
            enunciado = "Una esponja gigante puede filtrar diariamente:",
            opciones = listOf("1 litro", "10 litros", "Miles de litros de agua", "Nada"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.DIFICIL,
            categoria = "PORÍFEROS",
            especieId = 68
        ),
        PreguntaTrivia(
            id = 809,
            enunciado = "¿Qué forma tiene la Aplysina cauliformis (Esponja Cuerda)?",
            opciones = listOf("Ramificada o de cuerda", "De barril", "De oreja", "Plana"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.DIFICIL,
            categoria = "PORÍFEROS",
            especieId = 180
        ),
        PreguntaTrivia(
            id = 810,
            enunciado = "Esta esponja es famosa por emitir un olor fuerte al manipularla:",
            opciones = listOf("Esponja Dedo", "Esponja Roja", "Esponja de Fuego", "Esponja Hedionda"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.DIFICIL,
            categoria = "PORÍFEROS",
            especieId = 28
        )
    )

    private val preguntasCrustaceos = listOf(
        PreguntaTrivia(
            id = 901,
            enunciado = "Cangrejo común en manglares y estuarios de color azul verdoso:",
            opciones = listOf("Cangrejo Azul", "Cangrejo Rey", "Cangrejo Flecha", "Cangrejo de Arrecife"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "CRUSTÁCEOS",
            especieId = 44
        ),
        PreguntaTrivia(
            id = 902,
            enunciado = "¿Qué utiliza la Langosta Espinosa para defenderse?",
            opciones = listOf("Pinzas gigantes", "Sus largas antenas", "Tinta", "Camuflaje invisible"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "CRUSTÁCEOS",
            especieId = 4
        ),
        PreguntaTrivia(
            id = 903,
            enunciado = "Estos crustáceos son famosos por limpiar parásitos de peces y tortugas:",
            opciones = listOf("Cangrejo Azul", "Langosta Moteada", "Camarón Limpiador", "Cangrejo Ermitaño"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "CRUSTÁCEOS",
            especieId = 64
        ),
        PreguntaTrivia(
            id = 904,
            enunciado = "Gran cangrejo que ayuda a los arrecifes comiendo el exceso de algas:",
            opciones = listOf("Cangrejo Flecha", "Cangrejo de Arrecife", "Cangrejo Sally", "Cangrejo Rey"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "CRUSTÁCEOS",
            especieId = 14
        ),
        PreguntaTrivia(
            id = 905,
            enunciado = "El Cangrejo Sally Lightfoot se caracteriza por ser muy:",
            opciones = listOf("Lento", "Rápido y ágil", "Agresivo", "Grande"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "CRUSTÁCEOS",
            especieId = 34
        ),
        PreguntaTrivia(
            id = 906,
            enunciado = "¿Qué necesita el Cangrejo Ermitaño Terrestre para proteger su cuerpo?",
            opciones = listOf("Una concha de caracol vacía", "Arena húmeda", "Piedras planas", "Ramas de mangle"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "CRUSTÁCEOS",
            especieId = 114
        ),
        PreguntaTrivia(
            id = 907,
            enunciado = "El Camarón Chasqueador produce un sonido fuerte para:",
            opciones = listOf("Llamar a su madre", "Cantar", "Aturdir a sus presas", "Atraer delfines"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.DIFICIL,
            categoria = "CRUSTÁCEOS",
            especieId = 54
        ),
        PreguntaTrivia(
            id = 908,
            enunciado = "El Cangrejo Flecha se reconoce por sus patas:",
            opciones = listOf("Cortas y peludas", "Azules", "Anchas como remos", "Extremadamente largas y delgadas"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.DIFICIL,
            categoria = "CRUSTÁCEOS",
            especieId = 124
        ),
        PreguntaTrivia(
            id = 909,
            enunciado = "A diferencia de la común, la Langosta Moteada es:",
            opciones = listOf("Gigante", "Más pequeña y con manchas", "De agua dulce", "Sin patas"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.DIFICIL,
            categoria = "CRUSTÁCEOS",
            especieId = 158
        ),
        PreguntaTrivia(
            id = 910,
            enunciado = "La Langosta Zapatilla se distingue por no tener:",
            opciones = listOf("Ojos", "Cola", "Antenas largas", "Caparazón duro"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.DIFICIL,
            categoria = "CRUSTÁCEOS",
            especieId = 161
        )
    )

    private val preguntasEquinodermos = listOf(
        PreguntaTrivia(
            id = 1001,
            enunciado = "Gran estrella de mar de color naranja o marrón común en pastos marinos:",
            opciones = listOf("Estrella Cometa", "Estrella Frágil", "Estrella Almohada", "Estrella Negra"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "EQUINODERMOS",
            especieId = 37
        ),
        PreguntaTrivia(
            id = 1002,
            enunciado = "Este erizo es vital para el arrecife porque controla el crecimiento de algas:",
            opciones = listOf("Erizo Negro", "Erizo Blanco", "Erizo Café", "Erizo Lápiz"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "EQUINODERMOS",
            especieId = 7
        ),
        PreguntaTrivia(
            id = 1003,
            enunciado = "¿Cómo se alimenta principalmente el Pepino de Mar Tigre?",
            opciones = listOf("Cazando peces", "Comiendo tortugas", "Filtrando plancton", "Materia orgánica del sedimento"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "EQUINODERMOS",
            especieId = 57
        ),
        PreguntaTrivia(
            id = 1004,
            enunciado = "Equinodermo aplanado en forma de disco que vive semienterrado en la arena:",
            opciones = listOf("Estrella de Mar", "Galleta de Mar", "Pepino de Mar", "Erizo de Mar"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.FACIL,
            categoria = "EQUINODERMOS",
            especieId = 127
        ),
        PreguntaTrivia(
            id = 1005,
            enunciado = "¿De qué se alimenta principalmente el Erizo Blanco?",
            opciones = listOf("Algas y pastos marinos", "Peces pequeños", "Corales", "Detritos profundos"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.FACIL,
            categoria = "EQUINODERMOS",
            especieId = 17
        ),
        PreguntaTrivia(
            id = 1006,
            enunciado = "La Estrella de Mar Cometa es famosa por su gran capacidad de:",
            opciones = listOf("Volar", "Cantar", "Regeneración", "Cambiar de sexo"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.FACIL,
            categoria = "EQUINODERMOS",
            especieId = 47
        ),
        PreguntaTrivia(
            id = 1007,
            enunciado = "El Erizo Lápiz recibe su nombre por tener espinas:",
            opciones = listOf("Muy largas y finas", "Transparentes", "Flexibles", "Gruesas y cortas"),
            respuestaCorrecta = 4,
            dificultad = Dificultad.FACIL,
            categoria = "EQUINODERMOS",
            especieId = 97
        ),
        PreguntaTrivia(
            id = 1008,
            enunciado = "El Pepino de Mar ayuda al ecosistema reciclando:",
            opciones = listOf("Plástico", "Nutrientes del fondo", "Agua salada", "Luz solar"),
            respuestaCorrecta = 2,
            dificultad = Dificultad.DIFICIL,
            categoria = "EQUINODERMOS",
            especieId = 67
        ),
        PreguntaTrivia(
            id = 1009,
            enunciado = "Se diferencia de otras estrellas por sus brazos muy delgados, móviles y frágiles:",
            opciones = listOf("Estrella Frágil Negra", "Estrella Almohada", "Estrella Cometa", "Estrella Roja"),
            respuestaCorrecta = 1,
            dificultad = Dificultad.DIFICIL,
            categoria = "EQUINODERMOS",
            especieId = 87
        ),
        PreguntaTrivia(
            id = 1010,
            enunciado = "¿Cuántas filas de tubérculos tiene en su cuerpo el Pepino de Mar de cinco dientes?",
            opciones = listOf("Una", "Tres", "Cinco", "Diez"),
            respuestaCorrecta = 3,
            dificultad = Dificultad.DIFICIL,
            categoria = "EQUINODERMOS",
            especieId = 77
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
