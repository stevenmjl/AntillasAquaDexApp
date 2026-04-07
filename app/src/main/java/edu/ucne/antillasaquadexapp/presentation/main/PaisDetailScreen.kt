package edu.ucne.antillasaquadexapp.presentation.main

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucne.antillasaquadexapp.domain.model.Paises
import edu.ucne.antillasaquadexapp.domain.model.Zona
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaisDetailScreen(
    nombrePais: String,
    onNavigateBack: () -> Unit,
    onZoneClick: (Zona) -> Unit
) {
    val pais = Paises.find { it.nombre == nombrePais }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(nombrePais, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        if (pais != null) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(0.dp)
            ) {
                items(pais.zonas) { zona ->
                    ZonaCard(
                        zona = zona,
                        modifier = Modifier.fillMaxHeight(0.5f),
                        onClick = { onZoneClick(zona) }
                    )
                }
            }
        }
    }
}

@Composable
fun ZonaCard(
    zona: Zona,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(350.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = zona.imagenResId),
            contentDescription = zona.nombre,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black.copy(alpha = 0.3f)
        ) {}

        Text(
            text = zona.nombre.uppercase(),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        )
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

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PaisDetailDarkPreview() {
    AntillasAquaDexAppTheme {
        PaisDetailScreen(
            nombrePais = "Cuba",
            onNavigateBack = {},
            onZoneClick = {}
        )
    }
}
