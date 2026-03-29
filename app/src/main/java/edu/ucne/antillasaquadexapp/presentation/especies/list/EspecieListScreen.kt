package edu.ucne.antillasaquadexapp.presentation.especies.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import edu.ucne.antillasaquadexapp.domain.model.Especie
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@Composable
fun EspecieListScreen(
    viewModel: EspecieListViewModel = hiltViewModel(),
    onEspecieClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EspecieListContent(
        state = state,
        onEspecieClick = onEspecieClick,
        onBusquedaChange = viewModel::onBusquedaChange,
        onLimpiarBusqueda = viewModel::limpiarBusqueda,
        onFiltroTipoChange = viewModel::onFiltroTipoChange,
        onRetry = viewModel::loadEspecies
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EspecieListContent(
    state: EspecieListUiState,
    onEspecieClick: (Int) -> Unit,
    onBusquedaChange: (String) -> Unit,
    onLimpiarBusqueda: () -> Unit,
    onFiltroTipoChange: (FiltroTipo) -> Unit,
    onRetry: () -> Unit
) {
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(title = { Text("Enciclopedia marina") })
                SearchBar(
                    query = state.busqueda,
                    onQueryChange = onBusquedaChange,
                    onClear = onLimpiarBusqueda
                )
                FilterChips(
                    selectedType = state.filtroTipo,
                    onTypeSelected = onFiltroTipoChange
                )
            }
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
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Error al cargar especies",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = onRetry) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Reintentar")
                    }
                }
            } else if (state.especiesFiltradas.isEmpty()) {
                Text(
                    text = "No se encontraron especies",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.especiesFiltradas) { especie ->
                        EspecieListItem(especie = especie, onClick = { onEspecieClick(especie.especieId) })
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = { Text("Buscar especie...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(Icons.Default.Clear, contentDescription = "Limpiar")
                }
            }
        },
        singleLine = true,
        shape = MaterialTheme.shapes.medium
    )
}

@Composable
fun FilterChips(
    selectedType: FiltroTipo,
    onTypeSelected: (FiltroTipo) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selectedType == FiltroTipo.TODOS,
            onClick = { onTypeSelected(FiltroTipo.TODOS) },
            label = { Text("Todos") }
        )
        FilterChip(
            selected = selectedType == FiltroTipo.ANIMAL,
            onClick = { onTypeSelected(FiltroTipo.ANIMAL) },
            label = { Text("Animales") }
        )
        FilterChip(
            selected = selectedType == FiltroTipo.PLANTA,
            onClick = { onTypeSelected(FiltroTipo.PLANTA) },
            label = { Text("Plantas") }
        )
    }
}

@Composable
fun EspecieListItem(especie: Especie, onClick: () -> Unit) {
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
            Column {
                Text(
                    text = especie.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = especie.nombreCientifico,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
                Text(
                    text = "Ver detalle >",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EspecieListPreview() {
    AntillasAquaDexAppTheme {
        EspecieListContent(
            state = EspecieListUiState(
                especiesFiltradas = listOf(
                    Especie(1, "Manatí", "Trichechus manatus", "Animal", true, "", "", "", 500.12, "", "", "", ""),
                    Especie(2, "Coral Cerebro", "Diploria labyrinthiformis", "Animal", true, "", "", "", 100.10, "", "", "", "")
                )
            ),
            onEspecieClick = {},
            onBusquedaChange = {},
            onLimpiarBusqueda = {},
            onFiltroTipoChange = {},
            onRetry = {}
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun EspecieListDarkPreview() {
    AntillasAquaDexAppTheme {
        EspecieListContent(
            state = EspecieListUiState(
                especiesFiltradas = emptyList(),
                isLoading = false
            ),
            onEspecieClick = {},
            onBusquedaChange = {},
            onLimpiarBusqueda = {},
            onFiltroTipoChange = {},
            onRetry = {}
        )
    }
}
