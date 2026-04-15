package edu.ucne.antillasaquadexapp.presentation.main

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Sailing
import androidx.compose.material.icons.filled.SetMeal
import androidx.compose.material.icons.filled.Water
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucne.antillasaquadexapp.domain.model.Paises
import edu.ucne.antillasaquadexapp.presentation.components.AquaDexInfoDialog
import edu.ucne.antillasaquadexapp.presentation.components.AquaDexTopBar
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onPaisClick: (String) -> Unit
) {
    // Estados para el diálogo de información
    var showDialog by remember { mutableStateOf(false) }
    var selectedPaisName by remember { mutableStateOf("") }
    var selectedDescription by remember { mutableStateOf("") }
    var selectedIcon by remember { mutableStateOf(Icons.Default.Waves) }

    Scaffold(
        topBar = {
            AquaDexTopBar(title = "Antillas AquaDex")
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Explora los países",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(Paises) { pais ->
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onPaisClick(pais.nombre) },
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
                        shape = MaterialTheme.shapes.extraLarge
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = pais.nombre,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                            
                            // Contenedor de Imagen con Botón "?"
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = pais.imagenResId),
                                    contentDescription = "Mapa de ${pais.nombre}",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                                
                                // Botón flotante "?" en la esquina inferior derecha
                                IconButton(
                                    onClick = {
                                        val (desc, icon) = when (pais.nombre) {
                                            "República Dominicana" -> Pair(
                                                "El corazón de las Antillas, abrazado por el Atlántico y el Caribe. Sus aguas son santuarios de ballenas jorobadas y cuna de arrecifes de coral vibrantes donde habitan manatíes, tortugas carey y una infinita diversidad de peces tropicales.",
                                                Icons.Default.Waves
                                            )
                                            "Puerto Rico" -> Pair(
                                                "La Isla del Encanto posee bahías bioluminiscentes mágicas y paredes de coral impresionantes. Sus costas son hogar de delfines juguetones y una rica vida bentónica que se esconde entre manglares protegidos.",
                                                Icons.Default.Sailing
                                            )
                                            "Cuba" -> Pair(
                                                "Un archipiélago de biodiversidad inigualable. Sus 'Jardines de la Reina' son de los arrecifes mejor conservados del mundo, donde abundan tiburones majestuosos, meros gigantes y praderas de pastos marinos vitales.",
                                                Icons.Default.SetMeal
                                            )
                                            "Jamaica" -> Pair(
                                                "Tierra de aguas profundas y corales negros raros. Sus costas norteñas ofrecen acantilados submarinos y una fauna pelágica vibrante que surca las corrientes del canal de Jamaica.",
                                                Icons.Default.Anchor
                                            )
                                            "Haití" -> Pair(
                                                "Un tesoro marino por redescubrir, con arrecifes profundos y una geografía costera única que sirve de refugio a especies migratorias y comunidades de peces de roca en sus aguas turquesas.",
                                                Icons.Default.Public
                                            )
                                            else -> Pair("Explora la biodiversidad única de este rincón del Caribe.", Icons.Default.Water)
                                        }
                                        selectedPaisName = pais.nombre
                                        selectedDescription = desc
                                        selectedIcon = icon
                                        showDialog = true
                                    },
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(12.dp)
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f))
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.HelpOutline,
                                        contentDescription = "Información",
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Diálogo de información
    if (showDialog) {
        AquaDexInfoDialog(
            title = selectedPaisName,
            description = selectedDescription,
            icon = selectedIcon,
            onDismiss = { showDialog = false }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    AntillasAquaDexAppTheme {
        MainScreen(onPaisClick = {})
    }
}
