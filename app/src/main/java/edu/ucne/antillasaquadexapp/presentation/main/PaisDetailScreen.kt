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
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Water
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucne.antillasaquadexapp.domain.model.Paises
import edu.ucne.antillasaquadexapp.domain.model.Zona
import edu.ucne.antillasaquadexapp.presentation.components.AquaDexInfoDialog
import edu.ucne.antillasaquadexapp.presentation.components.AquaDexTopBar
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaisDetailScreen(
    nombrePais: String,
    onNavigateBack: () -> Unit,
    onZoneClick: (Zona) -> Unit
) {
    val pais = Paises.find { it.nombre == nombrePais }
    
    // Estados para el diálogo de información de zona
    var showDialog by remember { mutableStateOf(false) }
    var selectedZonaName by remember { mutableStateOf("") }
    var selectedDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AquaDexTopBar(
                title = nombrePais,
                onNavigateBack = onNavigateBack
            )
        }
    ) { padding ->
        if (pais != null) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(pais.zonas) { zona ->
                        ZonaCard(
                            zona = zona,
                            onInfoClick = {
                                val desc = when (zona.nombre) {
                                    // República Dominicana
                                    "Bahía de Samaná" -> "Santuario ancestral donde las ballenas jorobadas danzan cada invierno. Sus aguas tranquilas esconden praderas de pastos marinos y una biodiversidad que define el espíritu del Caribe."
                                    "Jaragua" -> "Un paisaje de contrastes donde la laguna de Oviedo se encuentra con el mar. Hogar de flamencos rosados y manatíes que navegan entre manglares y arrecifes de coral virgen."
                                    
                                    // Cuba
                                    "Jardines de la Reina" -> "Un edén submarino detenido en el tiempo. Laberintos de coral negro y bosques de mangle que albergan las poblaciones de tiburones y meros más saludables de todo el archipiélago."
                                    "Ciénaga de Zapata" -> "El humedal más grande del Caribe, donde el agua dulce y salada se abrazan para crear refugios únicos para el manatí del Caribe y una infinidad de especies marinas tropicales."
                                    
                                    // Puerto Rico
                                    "Isla de Mona" -> "Conocida como 'las Galápagos del Caribe', esta reserva remota posee acantilados submarinos impresionantes y arrecifes donde las tortugas y peces pelágicos reinan en total libertad."
                                    "Bahía de Vieques" -> "Un paraíso de playas salvajes y canales de manglar. Sus profundidades guardan secretos de la historia marina y comunidades de vida marina que regresan siempre a sus arenas blancas."
                                    
                                    // Jamaica
                                    "Montego Bay" -> "Parque marino pionero con jardines de coral vibrantes. Sus aguas cristalinas son hogar de anémonas, rayas y una colorida variedad de peces que habitan en sus arrecifes protegidos."
                                    "Blue Lagoon" -> "Una joya mística donde manantiales de agua dulce se encuentran con el mar profundo. Sus tonos azul cobalto esconden una fauna única adaptada a la mezcla de temperaturas y corrientes."
                                    
                                    else -> "Explora los secretos naturales de esta reserva, un refugio vital para la vida marina del Caribe."
                                }
                                selectedZonaName = zona.nombre
                                selectedDescription = desc
                                showDialog = true
                            },
                            onClick = { onZoneClick(zona) }
                        )
                    }
                }
            }
        }
    }

    // Diálogo de información
    if (showDialog) {
        AquaDexInfoDialog(
            title = selectedZonaName,
            description = selectedDescription,
            icon = Icons.Default.Water,
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
fun ZonaCard(
    zona: Zona,
    modifier: Modifier = Modifier,
    onInfoClick: () -> Unit,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(16.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.extraLarge,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = zona.imagenResId),
                contentDescription = zona.nombre,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Overlay para legibilidad
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black.copy(alpha = 0.35f)
            ) {}

            // Botón "?" en la esquina superior derecha
            IconButton(
                onClick = onInfoClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.25f))
            ) {
                Icon(
                    imageVector = Icons.Default.HelpOutline,
                    contentDescription = "Información de zona",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }

            // Textos de la Zona
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp)
            ) {
                Text(
                    text = "RESERVA NATURAL",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = androidx.compose.ui.unit.TextUnit.Unspecified
                )
                Text(
                    text = zona.nombre,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaisDetailPreview() {
    AntillasAquaDexAppTheme {
        PaisDetailScreen(
            nombrePais = "República Dominicana",
            onNavigateBack = {},
            onZoneClick = {}
        )
    }
}
