package edu.ucne.antillasaquadexapp.presentation.trivia

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import edu.ucne.antillasaquadexapp.R
import edu.ucne.antillasaquadexapp.data.local.model.Dificultad
import edu.ucne.antillasaquadexapp.data.local.model.PreguntaTrivia
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@Composable
fun TriviaScreen(
    onNavigateBack: () -> Unit,
    viewModel: TriviaViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    if (state.isPlaying) {
        TriviaContent(
            state = state,
            onNavigateBack = { viewModel.toggleConfirmacionSalir() },
            onResponder = { viewModel.responder(it) },
            onToggleAyuda = { viewModel.toggleAyuda() },
            onConfirmarSalir = { viewModel.detenerTrivia() },
            onCancelarSalir = { viewModel.toggleConfirmacionSalir() }
        )
    } else {
        TriviaMenu(
            onCategorySelected = { viewModel.iniciarTrivia(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaMenu(
    onCategorySelected: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Zona de Trivia", fontWeight = FontWeight.Bold) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_trivia),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "¡Pon a prueba tus conocimientos!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Aprende sobre la vida marina del Caribe. Gana medallas y desbloquea logros.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Selecciona una Categoría",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                val categorias = listOf(
                    TriviaCategoria("PECES", "Explora el mundo de los peces del Caribe", Icons.Default.SetMeal, Color(0xFF4FC3F7), R.drawable.img_medalla_peces),
                    TriviaCategoria("PLANTAS", "Próximamente", Icons.Default.Park, Color(0xFF81C784), habilitada = false),
                    TriviaCategoria("AVES", "Próximamente", Icons.Default.AirplanemodeActive, Color(0xFFFFB74D), habilitada = false)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(categorias) { categoria ->
                        CategoryCard(
                            categoria = categoria,
                            onClick = { if (categoria.habilitada) onCategorySelected(categoria.nombre) }
                        )
                    }
                }
            }
        }
    }
}

data class TriviaCategoria(
    val nombre: String,
    val descripcion: String,
    val icono: ImageVector,
    val color: Color,
    val medallaResId: Int? = null,
    val habilitada: Boolean = true
)

@Composable
fun CategoryCard(categoria: TriviaCategoria, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.8f)
            .clickable(enabled = categoria.habilitada) { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (categoria.habilitada) Color.White else Color.LightGray.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(categoria.color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                if (categoria.medallaResId != null) {
                    Image(
                        painter = painterResource(id = categoria.medallaResId),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                } else {
                    Icon(
                        imageVector = categoria.icono,
                        contentDescription = null,
                        tint = categoria.color,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = categoria.nombre,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = categoria.descripcion,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaContent(
    state: TriviaUiState,
    onNavigateBack: () -> Unit,
    onResponder: (Int) -> Unit,
    onToggleAyuda: () -> Unit,
    onConfirmarSalir: () -> Unit,
    onCancelarSalir: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Trivia: ${state.categoria}", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = onToggleAyuda) {
                        Icon(Icons.Default.HelpOutline, contentDescription = "Ayuda")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    repeat(3) { index ->
                        Icon(
                            imageVector = if (index < state.vidas) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Vida",
                            tint = if (index < state.vidas) Color.Red else Color.Gray,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                val maxTiempo = if (state.preguntas.getOrNull(state.preguntaActualIndex)?.dificultad == Dificultad.DIFICIL) 30f else 40f
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = { state.tiempoRestante / maxTiempo },
                        modifier = Modifier.size(50.dp),
                        strokeWidth = 4.dp,
                        color = if (state.tiempoRestante > 10) MaterialTheme.colorScheme.primary else Color.Red
                    )
                    Text(
                        text = state.tiempoRestante.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (state.isLoading) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (state.preguntas.isNotEmpty()) {
                val pregunta = state.preguntas.getOrNull(state.preguntaActualIndex)
                if (pregunta != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (state.imagenEspecieUrl != null) {
                                AsyncImage(
                                    model = state.imagenEspecieUrl,
                                    contentDescription = "Especie",
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(MaterialTheme.colorScheme.surfaceVariant),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }

                            Text(
                                text = "Pregunta ${state.preguntaActualIndex + 1} / ${state.preguntas.size}",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.secondary
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = pregunta.enunciado,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        pregunta.opciones.forEachIndexed { index, opcion ->
                            val colorBase = when {
                                state.esCorrecto == true && pregunta.respuestaCorrecta == index -> Color(0xFF4CAF50)
                                state.esCorrecto == false && index == pregunta.respuestaCorrecta -> Color(0xFF4CAF50)
                                state.esCorrecto == false && state.mensajeRespuesta != null -> Color(0xFFE57373)
                                else -> MaterialTheme.colorScheme.primaryContainer
                            }

                            Button(
                                onClick = { if (state.esCorrecto == null) onResponder(index) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorBase,
                                    contentColor = if (state.esCorrecto != null) Color.White else MaterialTheme.colorScheme.onPrimaryContainer
                                ),
                                enabled = state.esCorrecto == null
                            ) {
                                Text(text = opcion, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }
    }

    if (state.isGameOver) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("¡Game Over!", fontWeight = FontWeight.Bold) },
            text = { Text("Te quedaste sin vidas. ¡Sigue aprendiendo para mejorar!") },
            confirmButton = {
                Button(onClick = onConfirmarSalir) { Text("Volver al Menú") }
            }
        )
    }

    if (state.isVictory) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("¡Felicidades!", fontWeight = FontWeight.Bold) },
            text = { Text("Has completado la trivia de ${state.categoria}. ¡Has ganado una medalla!") },
            confirmButton = {
                Button(onClick = onConfirmarSalir) { Text("¡Genial!") }
            }
        )
    }

    if (state.mostrarAyuda) {
        AlertDialog(
            onDismissRequest = onToggleAyuda,
            title = { Text("Reglas de la Trivia") },
            text = {
                Text(
                    "1. Tienes 3 vidas.\n" +
                            "2. Las preguntas fáciles tienen 40s y las difíciles 30s.\n" +
                            "3. Si fallas o el tiempo se agota, pierdes una vida.\n" +
                            "4. ¡Completa todas para ganar medallas!"
                )
            },
            confirmButton = {
                TextButton(onClick = onToggleAyuda) { Text("Entendido") }
            }
        )
    }

    if (state.mostrarConfirmacionSalir) {
        AlertDialog(
            onDismissRequest = onCancelarSalir,
            title = { Text("¿Abandonar Trivia?") },
            text = { Text("Si sales ahora, perderás tu progreso actual en esta categoría. ¿Estás seguro?") },
            confirmButton = {
                Button(
                    onClick = onConfirmarSalir,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) { Text("Salir") }
            },
            dismissButton = {
                TextButton(onClick = onCancelarSalir) { Text("Continuar Jugando") }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TriviaMenuPreview() {
    AntillasAquaDexAppTheme {
        TriviaMenu(onCategorySelected = {})
    }
}

@Preview(showBackground = true)
@Composable
fun TriviaQuestionPreview() {
    AntillasAquaDexAppTheme {
        TriviaContent(
            state = TriviaUiState(
                isPlaying = true,
                isLoading = false,
                preguntas = listOf(
                    PreguntaTrivia(
                        id = 1,
                        enunciado = "¿Esta especie es un pez?",
                        opciones = listOf("Sí", "No"),
                        respuestaCorrecta = 0,
                        dificultad = Dificultad.FACIL,
                        categoria = "PECES"
                    )
                ),
                preguntaActualIndex = 0,
                vidas = 3,
                tiempoRestante = 40,
                categoria = "PECES"
            ),
            onNavigateBack = {},
            onResponder = {},
            onToggleAyuda = {},
            onConfirmarSalir = {},
            onCancelarSalir = {}
        )
    }
}
