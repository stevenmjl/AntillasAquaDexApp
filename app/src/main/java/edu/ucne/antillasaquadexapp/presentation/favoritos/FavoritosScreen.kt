package edu.ucne.antillasaquadexapp.presentation.favoritos

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import edu.ucne.antillasaquadexapp.presentation.components.ConfirmaBorrarDialog
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@Composable
fun FavoritosScreen(
    viewModel: FavoritosViewModel = hiltViewModel(),
    onEspecieClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoritosContent(
        state = state,
        onEspecieClick = onEspecieClick,
        onRemoveFavorite = viewModel::removeFavorite
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritosContent(
    state: FavoritosUiState,
    onEspecieClick: (Int) -> Unit,
    onRemoveFavorite: (Int) -> Unit
) {
    var especieABorrar by remember { mutableStateOf<Especie?>(null) }

    if (especieABorrar != null) {
        ConfirmaBorrarDialog(
            nombreEspecie = especieABorrar!!.nombre,
            onConfirm = {
                onRemoveFavorite(especieABorrar!!.especieId)
                especieABorrar = null
            },
            onDismiss = {
                especieABorrar = null
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Mis favoritos") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.favoritos.isEmpty()) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = "No tienes favoritos aún",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = "Máximo 20 especies",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.favoritos) { especie ->
                        FavoriteItem(
                            especie = especie,
                            onClick = { onEspecieClick(especie.especieId) },
                            onRemove = { especieABorrar = especie }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteItem(
    especie: Especie,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
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
                    .size(70.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = especie.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = especie.grupo,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            IconButton(onClick = onRemove) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar de favoritos",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoritosPreview() {
    AntillasAquaDexAppTheme {
        FavoritosContent(
            state = FavoritosUiState(
                favoritos = listOf(
                    Especie(1, "Manatí", "Trichechus manatus", "Mamíferos", 300.0, "Vulnerable", "Descripción", "Hábitat", 450.0, "Herbívora", "Reproducción", "Longevidad", "", true),
                    Especie(2, "Delfín", "Delphinus delphis", "Mamíferos", 250.0, "Preocupación menor", "Descripción", "Hábitat", 150.0, "Carnívora", "Reproducción", "Longevidad", "", true)
                )
            ),
            onEspecieClick = {},
            onRemoveFavorite = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FavoritosDarkPreview() {
    AntillasAquaDexAppTheme {
        FavoritosContent(
            state = FavoritosUiState(favoritos = emptyList()),
            onEspecieClick = {},
            onRemoveFavorite = {}
        )
    }
}
