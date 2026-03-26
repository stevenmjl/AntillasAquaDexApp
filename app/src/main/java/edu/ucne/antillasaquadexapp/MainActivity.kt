package edu.ucne.antillasaquadexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

// Definición de los destinos de navegación
sealed class Destino(val route: String, val label: String, val icon: ImageVector) {
    object Mapa : Destino("mapa", "Mapa", Icons.Filled.Map)
    object Especies : Destino("especies", "Especies", Icons.AutoMirrored.Filled.List)
    object Favs : Destino("favs", "Favoritos", Icons.Filled.Favorite)
    object Perfil : Destino("perfil", "Perfil", Icons.Filled.Person)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AntillasAquaDexAppTheme {
                AntillasAppScreen()
            }
        }
    }
}

@Composable
fun AntillasAppScreen() {
    val rutasDestino = listOf(Destino.Mapa, Destino.Especies, Destino.Favs, Destino.Perfil)
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                rutasDestino.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        label = { Text(destination.label) },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.label
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Aquí cambiarías el contenido según el selectedIndex
        ThemeTestScreen(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ThemeTestScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Antillas AquaDex",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Button(onClick = { }) { Text("Botón Primario") }
        FilledTonalButton(onClick = { }) { Text("Botón Secundario Tonal") }
        ElevatedButton(onClick = { }) { Text("Botón Elevado") }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) { Text("Botón Terciario") }

        OutlinedButton(onClick = { }) { Text("Botón Outlined") }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ThemeTestPreview() {
    AntillasAquaDexAppTheme(dynamicColor = false) {
        AntillasAppScreen()
    }
}