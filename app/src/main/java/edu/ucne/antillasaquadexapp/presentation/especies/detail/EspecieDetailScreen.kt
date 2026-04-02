package edu.ucne.antillasaquadexapp.presentation.especies.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import edu.ucne.antillasaquadexapp.domain.model.Especie
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@Composable
fun EspecieDetailScreen(
    especieId: Int,
    viewModel: EspecieDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(especieId) {
        viewModel.loadEspecie(especieId)
    }

    EspecieDetailContent(
        state = state,
        onNavigateBack = onNavigateBack,
        onToggleFavorito = viewModel::toggleFavorito
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EspecieDetailContent(
    state: EspecieDetailUiState,
    onNavigateBack: () -> Unit,
    onToggleFavorito: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de especie") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                state.especie?.let { especie ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        AsyncImage(
                            model = especie.imagenUrl,
                            contentDescription = especie.nombre,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentScale = ContentScale.Crop
                        )

                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = especie.nombre,
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = especie.nombreCientifico,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontStyle = FontStyle.Italic,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }
                                IconButton(onClick = onToggleFavorito) {
                                    Icon(
                                        imageVector = if (especie.esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                        contentDescription = "Favorito",
                                        tint = if (especie.esFavorito) Color.Red else MaterialTheme.colorScheme.outline,
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = especie.descripcion,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Datos interesantes",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    InfoRow(icon = Icons.Default.Straighten, label = "Longitud", value = "${especie.longitudCm} cm")
                                    InfoRow(icon = Icons.Default.Balance, label = "Peso", value = "${especie.pesoKg} kg")
                                    InfoRow(icon = Icons.Default.Restaurant, label = "Dieta", value = especie.dieta)
                                    InfoRow(icon = Icons.Default.Group, label = "Grupo", value = especie.grupo)
                                    InfoRow(icon = Icons.Default.PriorityHigh, label = "Estado", value = especie.estado)
                                    InfoRow(icon = Icons.Default.Egg, label = "Reproducción", value = especie.infoReproduccion)
                                    InfoRow(icon = Icons.Default.Timer, label = "Longevidad", value = especie.longevidad)
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = "Hábitat",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = especie.habitat,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            
                            Spacer(modifier = Modifier.height(32.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EspecieDetailPreview() {
    AntillasAquaDexAppTheme {
        EspecieDetailContent(
            state = EspecieDetailUiState(
                especie = Especie(
                    1, "Ballena Jorobada", "Megaptera novaeangliae", "Mamíferos",
                    1500.0, "Preocupación menor", "Gigante marino conocido por sus cantos.","Océanos y mares, especialmente en la Bahía de Samaná.",
                    30000.00, "Krill y peces pequeños", "Vivíparo (una cría cada 2-3 años)", "80 - 90 años", "", true
                )
            ),
            onNavigateBack = {},
            onToggleFavorito = {}
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun EspecieDetailDarkPreview() {
    AntillasAquaDexAppTheme {
        EspecieDetailContent(
            state = EspecieDetailUiState(
                especie = Especie(
                    1, "Ballena Jorobada", "Megaptera novaeangliae", "Mamíferos",
                    1500.0, "Preocupación menor", "Gigante marino conocido por sus cantos.","Océanos y mares, especialmente en la Bahía de Samaná.",
                    30000.00, "Krill y peces pequeños", "Vivíparo (una cría cada 2-3 años)", "80 - 90 años", "", true
                )
            ),
            onNavigateBack = {},
            onToggleFavorito = {}
        )
    }
}
