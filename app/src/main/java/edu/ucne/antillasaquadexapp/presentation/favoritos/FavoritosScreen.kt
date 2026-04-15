package edu.ucne.antillasaquadexapp.presentation.favoritos

import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import edu.ucne.antillasaquadexapp.domain.model.Especie
import edu.ucne.antillasaquadexapp.presentation.components.AquaDexTopBar
import edu.ucne.antillasaquadexapp.presentation.components.ConfirmaBorrarDialog
import edu.ucne.antillasaquadexapp.presentation.usuario.UsuarioViewModel
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@Composable
fun FavoritosScreen(
    viewModel: FavoritosViewModel = hiltViewModel(),
    usuarioViewModel: UsuarioViewModel = hiltViewModel(),
    onEspecieClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoritosContent(
        state = state,
        onEspecieClick = onEspecieClick,
        onRemoveFavorite = viewModel::removeFavorite,
        onSetProfilePicture = usuarioViewModel::setProfilePicture,
        onReorder = viewModel::reorderFavorites
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FavoritosContent(
    state: FavoritosUiState,
    onEspecieClick: (Int) -> Unit,
    onRemoveFavorite: (Int) -> Unit,
    onSetProfilePicture: (String) -> Unit,
    onReorder: (Int, Int) -> Unit
) {
    var especieABorrar by remember { mutableStateOf<Especie?>(null) }
    var especieParaPerfil by remember { mutableStateOf<Especie?>(null) }
    
    val listState = rememberLazyListState()
    val haptic = LocalHapticFeedback.current
    
    var draggedItemId by remember { mutableStateOf<Int?>(null) }
    var dragAccumulatedOffset by remember { mutableFloatStateOf(0f) }
    
    val density = LocalDensity.current
    val spacingPx = with(density) { 12.dp.toPx() } 
    var itemHeightPx by remember { mutableFloatStateOf(0f) }

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

    if (especieParaPerfil != null) {
        AlertDialog(
            onDismissRequest = { especieParaPerfil = null },
            title = { Text("Foto de perfil") },
            text = { Text("¿Quieres usar a ${especieParaPerfil?.nombre} como tu foto de perfil?") },
            confirmButton = {
                TextButton(onClick = {
                    especieParaPerfil?.imagenUrl?.let { onSetProfilePicture(it) }
                    especieParaPerfil = null
                }) {
                    Text("¡Claro!")
                }
            },
            dismissButton = {
                TextButton(onClick = { especieParaPerfil = null }) {
                    Text("No")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            AquaDexTopBar(title = "Mi Top especies")
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.favoritos.isEmpty()) {
                EmptyFavoritos()
            } else {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(
                        items = state.favoritos,
                        key = { _, item -> item.especieId }
                    ) { index, especie ->
                        val isBeingDragged = draggedItemId == especie.especieId
                        val currentIndex by rememberUpdatedState(index)
                        
                        val elevation by animateDpAsState(
                            targetValue = if (isBeingDragged) 16.dp else 2.dp,
                            animationSpec = spring(stiffness = Spring.StiffnessMedium),
                            label = "elevation"
                        )
                        
                        FavoriteItem(
                            especie = especie,
                            rank = index + 1,
                            isDragging = isBeingDragged,
                            modifier = Modifier
                                .animateItem(
                                    placementSpec = if (isBeingDragged) null else spring(
                                        dampingRatio = Spring.DampingRatioLowBouncy,
                                        stiffness = Spring.StiffnessMediumLow
                                    )
                                )
                                .onSizeChanged { size ->
                                    if (size.height > 0) {
                                        itemHeightPx = size.height.toFloat()
                                    }
                                }
                                .zIndex(if (isBeingDragged) 100f else 1f)
                                .graphicsLayer {
                                    if (isBeingDragged) {
                                        translationY = dragAccumulatedOffset
                                        scaleX = 1.08f
                                        scaleY = 1.08f
                                    }
                                }
                                .shadow(elevation, MaterialTheme.shapes.large)
                                .pointerInput(especie.especieId) {
                                    detectDragGesturesAfterLongPress(
                                        onDragStart = {
                                            draggedItemId = especie.especieId
                                            dragAccumulatedOffset = 0f
                                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                        },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            
                                            if (itemHeightPx > 0) {
                                                dragAccumulatedOffset += dragAmount.y
                                                val step = itemHeightPx + spacingPx
                                                val threshold = step * 0.6f 
                                                
                                                if (dragAccumulatedOffset > threshold && currentIndex < state.favoritos.size - 1) {
                                                    onReorder(currentIndex, currentIndex + 1)
                                                    dragAccumulatedOffset -= step
                                                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                                } else if (dragAccumulatedOffset < -threshold && currentIndex > 0) {
                                                    onReorder(currentIndex, currentIndex - 1)
                                                    dragAccumulatedOffset += step
                                                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                                }
                                            }
                                        },
                                        onDragEnd = {
                                            draggedItemId = null
                                            dragAccumulatedOffset = 0f
                                        },
                                        onDragCancel = {
                                            draggedItemId = null
                                            dragAccumulatedOffset = 0f
                                        }
                                    )
                                },
                            onClick = { onEspecieClick(especie.especieId) },
                            onRemove = { especieABorrar = especie },
                            onSetProfile = { especieParaPerfil = especie }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyFavoritos() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Tu ranking está vacío",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "Agrega especies para armar tu Top 20",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
fun FavoriteItem(
    especie: Especie,
    rank: Int,
    isDragging: Boolean,
    onClick: () -> Unit,
    onRemove: () -> Unit,
    onSetProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rankColor = when(rank) {
        1 -> Color(0xFFFFD700) 
        2 -> Color(0xFFC0C0C0) 
        3 -> Color(0xFFCD7F32)
        else -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isDragging) MaterialTheme.colorScheme.surfaceColorAtElevation(12.dp) 
                            else MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(if (rank <= 3) 36.dp else 30.dp)
                    .clip(CircleShape)
                    .background(rankColor)
                    .then(
                        if (rank <= 3) Modifier.border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
                        else Modifier
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = rank.toString(),
                    style = if(rank <= 3) MaterialTheme.typography.titleMedium else MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Black,
                    color = if (rank <= 3) Color.DarkGray else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))

            AsyncImage(
                model = especie.imagenUrl,
                contentDescription = especie.nombre,
                modifier = Modifier
                    .size(60.dp)
                    .clip(MaterialTheme.shapes.small),
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
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row {
                IconButton(onClick = onSetProfile) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Perfil",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
                    )
                }
                Icon(
                    imageVector = Icons.Default.DragHandle,
                    contentDescription = "Arrastrar",
                    modifier = Modifier.padding(8.dp),
                    tint = MaterialTheme.colorScheme.outlineVariant
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
                    Especie(2, "Delfín", "Delphinus delphis", "Mamíferos", 250.0, "Preocupación menor", "Descripción", "Hábitat", 150.0, "Carnívora", "Reproducción", "Longevidad", "", true),
                    Especie(3, "Ballena jorobada", "Balaenoptera javanica", "Mamíferos", 100.0, "Vulnerable", "Descripción", "Hábitat", 500.0, "Herbívora", "Reproducción", "Longevidad", "", true)
                )
            ),
            onEspecieClick = {},
            onRemoveFavorite = {},
            onSetProfilePicture = {},
            onReorder = { _, _ -> }
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
            onRemoveFavorite = {},
            onSetProfilePicture = {},
            onReorder = { _, _ -> }
        )
    }
}
