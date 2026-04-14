package edu.ucne.antillasaquadexapp.presentation.usuario

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
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
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    // Guardamos el nombre original para poder cancelar
    var nombreTemporal by remember(state.isEditingName) { mutableStateOf(state.nombre) }

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
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Foto de Perfil
            Box(contentAlignment = Alignment.BottomEnd) {
                if (state.profilePictureUrl != null) {
                    AsyncImage(
                        model = state.profilePictureUrl,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Surface(
                        modifier = Modifier.size(150.dp),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.padding(30.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Nombre de Usuario y Edición
            AnimatedContent(
                targetState = state.isEditingName,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                }, label = ""
            ) { editing ->
                if (editing) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                            value = nombreTemporal,
                            onValueChange = { nombreTemporal = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            label = { Text("Nombre de usuario") },
                            singleLine = true,
                            shape = MaterialTheme.shapes.large,
                            textStyle = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center)
                        )
                        
                        LaunchedEffect(Unit) {
                            focusRequester.requestFocus()
                        }

                        Row(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
                        ) {
                            TextButton(
                                onClick = { onToggleEdit() },
                                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                            ) {
                                Icon(Icons.Default.Close, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Cancelar")
                            }

                            ElevatedButton(
                                onClick = {
                                    onNombreChange(nombreTemporal)
                                    onGuardar()
                                },
                                enabled = nombreTemporal.isNotBlank()
                            ) {
                                Icon(Icons.Default.Check, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Guardar")
                            }
                        }
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val nombreMostrar = if (state.nombre.isBlank()) "Pon tu nombre" else state.nombre
                        val colorNombre = if (state.nombre.isBlank()) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.onSurface
                        
                        Text(
                            text = nombreMostrar,
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            color = colorNombre,
                            textAlign = TextAlign.Center
                        )
                        IconButton(onClick = onToggleEdit) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar nombre",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Sección de Medallas
            Text(
                text = "Tus medallas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                items(state.medallas) { medalla ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(85.dp)
                            .clickable {
                                if (medalla.conseguida) {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(medalla.descripcion)
                                    }
                                }
                            }
                    ) {
                        Surface(
                            shape = CircleShape,
                            shadowElevation = if (medalla.conseguida) 4.dp else 0.dp,
                            color = if (medalla.conseguida) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ) {
                            Image(
                                painter = painterResource(id = medalla.imagenResId),
                                contentDescription = medalla.categoria,
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(8.dp),
                                colorFilter = if (medalla.conseguida) null else ColorFilter.tint(Color.Gray.copy(alpha = 0.6f))
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = medalla.categoria,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            color = if (medalla.conseguida) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Sección de Ajustes
            Text(
                text = "Ajustes de sonido",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = if (state.volumen > 0) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Slider(
                        value = state.volumen,
                        onValueChange = onVolumenChange,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "${(state.volumen * 100).roundToInt()}%",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.width(45.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
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
