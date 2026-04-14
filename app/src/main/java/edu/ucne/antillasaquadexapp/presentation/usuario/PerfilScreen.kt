package edu.ucne.antillasaquadexapp.presentation.usuario

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import edu.ucne.antillasaquadexapp.R
import edu.ucne.antillasaquadexapp.presentation.components.AquaDexTopBar
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme
import kotlinx.coroutines.launch
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

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AquaDexTopBar(title = "Mi perfil")
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.profilePictureUrl != null) {
                AsyncImage(
                    model = state.profilePictureUrl,
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

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
                text = "Tus medallas",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.medallas) { medalla ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(80.dp)
                            .clickable {
                                if (medalla.conseguida) {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(medalla.descripcion)
                                    }
                                }
                            }
                    ) {
                        Image(
                            painter = painterResource(id = medalla.imagenResId),
                            contentDescription = medalla.categoria,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape),
                            colorFilter = if (medalla.conseguida) null else ColorFilter.tint(Color.Gray.copy(alpha = 0.8f))
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = medalla.categoria,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (medalla.conseguida) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Ajustes de sonido",
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
