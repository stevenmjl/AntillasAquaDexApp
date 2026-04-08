package edu.ucne.antillasaquadexapp.presentation.trivia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaScreen(
    onNavigateBack: () -> Unit,
    viewModel: TriviaViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.iniciarTrivia("PECES")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Zona de Trivia: ${state.categoria}", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleAyuda() }) {
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
            // Vidas y Cronómetro
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

                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = { state.tiempoRestante / 20f },
                        modifier = Modifier.size(50.dp),
                        strokeWidth = 4.dp,
                        color = if (state.tiempoRestante > 5) MaterialTheme.colorScheme.primary else Color.Red
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
                CircularProgressIndicator()
            } else if (state.preguntas.isNotEmpty()) {
                val pregunta = state.preguntas[state.preguntaActualIndex]

                // Card de la Pregunta
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
                        // Imagen de la Especie (si existe)
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

                // Opciones de Respuesta
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
                            onClick = { if (state.esCorrecto == null) viewModel.responder(index) },
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

    // Diálogos de Estado
    if (state.isGameOver) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("¡Game Over!", fontWeight = FontWeight.Bold) },
            text = { Text("Te quedaste sin vidas. Perdiste en la pregunta sobre la ballena azul. ¡Sigue aprendiendo para mejorar!") },
            confirmButton = {
                Button(onClick = { onNavigateBack() }) { Text("Volver al Menú") }
            }
        )
    }

    if (state.isVictory) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("¡Felicidades!", fontWeight = FontWeight.Bold) },
            text = { Text("Has completado la trivia de Peces. ¡Has ganado una medalla!") },
            confirmButton = {
                Button(onClick = { onNavigateBack() }) { Text("¡Genial!") }
            }
        )
    }

    if (state.mostrarAyuda) {
        AlertDialog(
            onDismissRequest = { viewModel.toggleAyuda() },
            title = { Text("Reglas de la Trivia") },
            text = {
                Text(
                    "1. Tienes 3 vidas.\n" +
                    "2. Las preguntas fáciles tienen 20s y las difíciles 10s.\n" +
                    "3. Si fallas o el tiempo se agota, pierdes una vida.\n" +
                    "4. ¡Completa todas para ganar medallas!"
                )
            },
            confirmButton = {
                TextButton(onClick = { viewModel.toggleAyuda() }) { Text("Entendido") }
            }
        )
    }
}
