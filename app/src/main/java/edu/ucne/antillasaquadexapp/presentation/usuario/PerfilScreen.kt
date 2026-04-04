package edu.ucne.antillasaquadexapp.presentation.usuario

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme
import kotlin.math.roundToInt

@Composable
fun PerfilScreen(
    viewModel: UsuarioViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PerfilContent(
        state = state,
        onNombreChange = viewModel::onNombreChange,
        onToggleEdit = viewModel::toggleEditingName,
        onVolumenChange = viewModel::onVolumenChange,
        onGuardar = viewModel::guardarUsuario
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilContent(
    state: UsuarioUiState,
    onNombreChange: (String) -> Unit,
    onToggleEdit: () -> Unit,
    onVolumenChange: (Float) -> Unit,
    onGuardar: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var textFieldValue by remember { mutableStateOf(TextFieldValue(state.nombre)) }

    LaunchedEffect(state.isEditingName) {
        if (state.isEditingName) {
            textFieldValue = TextFieldValue(
                text = state.nombre,
                selection = TextRange(state.nombre.length)
            )
            focusRequester.requestFocus()
        }
    }

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
                value = if (state.isEditingName) textFieldValue else TextFieldValue(state.nombre),
                onValueChange = {
                    textFieldValue = it
                    if (it.text != state.nombre) {
                        onNombreChange(it.text)
                    }
                },
                label = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                singleLine = true,
                enabled = state.isEditingName,
                trailingIcon = {
                    IconButton(onClick = onToggleEdit) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar nombre",
                            tint = if (state.isEditingName) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Ajustes de Sonido",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Slider(
                    value = state.volumen,
                    onValueChange = onVolumenChange,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${(state.volumen * 100).roundToInt()}%",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.width(40.dp)
                )
            }

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
            state = UsuarioUiState(nombre = "Steven Javier", volumen = 0.7f, isEditingName = false),
            onNombreChange = {},
            onToggleEdit = {},
            onVolumenChange = {},
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
            state = UsuarioUiState(nombre = "Steven Javier", volumen = 0.7f, isEditingName = true),
            onNombreChange = {},
            onToggleEdit = {},
            onGuardar = {},
            onVolumenChange = {}
        )
    }
}
