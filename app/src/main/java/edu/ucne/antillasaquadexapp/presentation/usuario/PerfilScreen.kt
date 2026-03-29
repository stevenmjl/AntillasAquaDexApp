package edu.ucne.antillasaquadexapp.presentation.usuario

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@Composable
fun PerfilScreen(
    viewModel: UsuarioViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PerfilContent(
        state = state,
        onNombreChange = viewModel::onNombreChange,
        onGuardar = viewModel::guardarUsuario
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilContent(
    state: UsuarioUiState,
    onNombreChange: (String) -> Unit,
    onGuardar: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Mi perfil") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Información del usuario",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.nombre,
                onValueChange = onNombreChange,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onGuardar,
                modifier = Modifier.fillMaxWidth(),
                enabled = state.nombre.isNotBlank()
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Guardar cambios")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilPreview() {
    AntillasAquaDexAppTheme {
        PerfilContent(
            state = UsuarioUiState(nombre = "Steven Javier"),
            onNombreChange = {},
            onGuardar = {}
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PerfilDarkPreview() {
    AntillasAquaDexAppTheme {
        PerfilContent(
            state = UsuarioUiState(nombre = "Steven Javier"),
            onNombreChange = {},
            onGuardar = {}
        )
    }
}
