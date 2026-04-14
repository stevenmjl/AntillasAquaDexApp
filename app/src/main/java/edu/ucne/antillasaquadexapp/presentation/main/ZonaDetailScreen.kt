package edu.ucne.antillasaquadexapp.presentation.main

import android.content.res.Configuration
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import edu.ucne.antillasaquadexapp.domain.model.Clima
import edu.ucne.antillasaquadexapp.domain.model.Especie
import edu.ucne.antillasaquadexapp.presentation.clima.ClimaUiState
import edu.ucne.antillasaquadexapp.presentation.clima.ClimaViewModel
import edu.ucne.antillasaquadexapp.presentation.components.AquaDexTopBar
import edu.ucne.antillasaquadexapp.presentation.especies.list.EspecieListUiState
import edu.ucne.antillasaquadexapp.presentation.especies.list.EspecieListViewModel
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZonaDetailScreen(
    zoneName: String,
    lat: Double,
    lon: Double,
    listaEspeciesID: List<Int>,
    climaViewModel: ClimaViewModel = hiltViewModel(),
    especieViewModel: EspecieListViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onEspecieClick: (Int) -> Unit
) {
    val climaState by climaViewModel.state.collectAsStateWithLifecycle()
    val especieState by especieViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        climaViewModel.loadClima(lat, lon)
        especieViewModel.loadEspecies()
    }

    ZonaDetailContent(
        zoneName = zoneName,
        listaEspeciesID = listaEspeciesID,
        climaState = climaState,
        especieState = especieState,
        onNavigateBack = onNavigateBack,
        onEspecieClick = onEspecieClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZonaDetailContent(
    zoneName: String,
    listaEspeciesID: List<Int>,
    climaState: ClimaUiState,
    especieState: EspecieListUiState,
    onNavigateBack: () -> Unit,
    onEspecieClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            AquaDexTopBar(
                title = zoneName,
                onNavigateBack = onNavigateBack
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(Modifier.height(8.dp)) }
            
            item {
                ClimaCard(state = climaState)
            }

            item {
                Text(
                    text = "Especies en esta zona",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            val zonaEspecies = especieState.especies.filter { it.especieId in listaEspeciesID }

            if (especieState.isLoading && zonaEspecies.isEmpty()) {
                item {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            } else if (zonaEspecies.isEmpty()) {
                item {
                    Text("No hay especies registradas en esta zona.", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                items(zonaEspecies) { especie ->
                    EspecieZonaCard(especie = especie, onClick = { onEspecieClick(especie.especieId) })
                }
            }
            
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun ClimaCard(state: ClimaUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (state.isLoading) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
            } else if (state.error != null) {
                Text(text = "Clima no disponible", color = MaterialTheme.colorScheme.error)
            } else if (state.clima != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.WbSunny, 
                            contentDescription = null, 
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = state.clima.condicion, style = MaterialTheme.typography.labelMedium)
                        Text(
                            text = "${state.clima.temperatura.toInt()}°C", 
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Air, 
                            contentDescription = null, 
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "Viento", style = MaterialTheme.typography.labelMedium)
                        Text(
                            text = "${state.clima.vientoVelocidad.toInt()} km/h", 
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.WaterDrop, 
                            contentDescription = null, 
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "Humedad", style = MaterialTheme.typography.labelMedium)
                        Text(
                            text = "${state.clima.humedad}%", 
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EspecieZonaCard(especie: Especie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = especie.imagenUrl,
                contentDescription = especie.nombre,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = especie.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Ver detalle >",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ZonaDetailPreview() {
    AntillasAquaDexAppTheme {
        ZonaDetailContent(
            zoneName = "Bahía de Samaná",
            listaEspeciesID = listOf(1, 2),
            climaState = ClimaUiState(
                isLoading = false,
                clima = Clima(
                    condicion = "Soleado",
                    temperatura = 28.5,
                    vientoVelocidad = 15.0,
                    humedad = 70,
                    iconoUrl = ""
                )
            ),
            especieState = EspecieListUiState(
                isLoading = false,
                especies = listOf(
                    Especie(1, "Ballena Jorobada", "Megaptera novaeangliae", "Mamíferos", 1500.0, "Vulnerable", "Descripción", "Hábitat", 30000.0, "Krill", "Reproducción", "50 años", "", false),
                    Especie(2, "Manatí", "Trichechus manatus", "Mamíferos", 300.0, "Vulnerable", "Descripción", "Hábitat", 450.0, "Hierba", "Reproducción", "Longevidad", "", false)
                )
            ),
            onNavigateBack = {},
            onEspecieClick = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ZonaDetailDarkPreview() {
    AntillasAquaDexAppTheme {
        ZonaDetailContent(
            zoneName = "Bahía de Samaná",
            listaEspeciesID = listOf(1, 2),
            climaState = ClimaUiState(
                isLoading = false,
                clima = Clima(
                    condicion = "Despejado",
                    temperatura = 24.0,
                    vientoVelocidad = 10.0,
                    humedad = 65,
                    iconoUrl = ""
                )
            ),
            especieState = EspecieListUiState(
                isLoading = false,
                especies = listOf(
                    Especie(1, "Ballena Jorobada", "Megaptera novaeangliae", "Mamíferos", 1500.0, "Vulnerable", "Descripción", "Hábitat", 30000.0, "Krill", "Reproducción", "50 años", "", false)
                )
            ),
            onNavigateBack = {},
            onEspecieClick = {}
        )
    }
}
