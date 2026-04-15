package edu.ucne.antillasaquadexapp.presentation.trivia

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import edu.ucne.antillasaquadexapp.R
import edu.ucne.antillasaquadexapp.data.local.model.Dificultad
import edu.ucne.antillasaquadexapp.data.local.model.PreguntaTrivia
import edu.ucne.antillasaquadexapp.presentation.components.AquaDexTopBar
import edu.ucne.antillasaquadexapp.presentation.usuario.UsuarioViewModel
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@Composable
fun TriviaScreen(
    onNavigateBack: () -> Unit,
    viewModel: TriviaViewModel = hiltViewModel(),
    usuarioViewModel: UsuarioViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val usuarioState by usuarioViewModel.state.collectAsStateWithLifecycle()

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
            nombreUsuario = usuarioState.nombre,
            onCategorySelected = { viewModel.iniciarTrivia(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaMenu(
    nombreUsuario: String,
    onCategorySelected: (String) -> Unit
) {
    Scaffold(
        topBar = {
            AquaDexTopBar(title = "Zona de Trivia")
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
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
                                    Color.Black.copy(alpha = 0.8f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "¡Pon a prueba tus conocimientos!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Aprende sobre la vida marina del Caribe y gana medallas exclusivas.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                val saludoText = buildAnnotatedString {
                    append("Selecciona una categoría, ")
                    withStyle(style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )) {
                        append(if (nombreUsuario.isBlank()) "Explorador" else nombreUsuario)
                    }
                    append(".")
                }

                Text(
                    text = saludoText,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(20.dp))

                val categorias = listOf(
                    TriviaCategoria("PECES", "Peces óseos y cartilaginosos", Icons.Default.SetMeal, Color(0xFF4FC3F7), R.drawable.img_medalla_peces),
                    TriviaCategoria("MAMÍFEROS", "Delfines, manatíes y ballenas", Icons.Default.Pets, Color(0xFF64B5F6), R.drawable.img_medalla_mamiferos),
                    TriviaCategoria("AVES", "Aves costeras y marinas", Icons.Default.Flight, Color(0xFFFFD54F), R.drawable.img_medalla_aves),
                    TriviaCategoria("PLANTAS", "Manglares y flora marina", Icons.Default.Eco, Color(0xFF81C784), R.drawable.img_medalla_plantas),
                    TriviaCategoria("MOLUSCOS", "Caracoles, pulpos y bivalvos", Icons.Default.BakeryDining, Color(0xFFBA68C8), R.drawable.img_medalla_moluscos),
                    TriviaCategoria("REPTILES", "Tortugas y reptiles marinos", Icons.Default.WbSunny, Color(0xFFFF8A65), R.drawable.img_medalla_reptiles),
                    TriviaCategoria("CNIDARIOS", "Corales, medusas y anémonas", Icons.Default.Waves, Color(0xFF4DB6AC), R.drawable.img_medalla_cnidarios),
                    TriviaCategoria("PORÍFEROS", "Esponjas de mar", Icons.Default.BubbleChart, Color(0xFF90A4AE), R.drawable.img_medalla_poliferos),
                    TriviaCategoria("CRUSTÁCEOS", "Langostas, cangrejos y camarones", Icons.Default.Tsunami, Color(0xFFE57373), R.drawable.img_medalla_crustaceos),
                    TriviaCategoria("EQUINODERMOS", "Estrellas y erizos de mar", Icons.Default.Star, Color(0xFFFFF176), R.drawable.img_medalla_equinodermos)
                )

                categorias.forEach { categoria ->
                    CategoryCard(
                        categoria = categoria,
                        onClick = { if (categoria.habilitada) onCategorySelected(categoria.nombre) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
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
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = categoria.habilitada) { onClick() },
        shape = MaterialTheme.shapes.extraLarge,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (categoria.habilitada) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(70.dp),
                shape = CircleShape,
                color = categoria.color.copy(alpha = 0.15f)
            ) {
                Box(contentAlignment = Alignment.Center) {
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
            }
            
            Spacer(modifier = Modifier.width(20.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = categoria.nombre,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = categoria.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (categoria.habilitada) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
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
            AquaDexTopBar(
                title = "Trivia: ${state.categoria}",
                onNavigateBack = onNavigateBack,
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
        TriviaMenu(nombreUsuario = "Pedro", onCategorySelected = {})
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
