package edu.ucne.antillasaquadexapp.presentation.usuario

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@Composable
fun BienvenidaScreen(
    viewModel: UsuarioViewModel = hiltViewModel(),
    onContinue: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BienvenidaContent(
        state = state,
        onNombreChange = viewModel::onNombreChange,
        onGuardar = viewModel::guardarUsuario,
        onContinue = onContinue
    )
}

@Composable
fun BienvenidaContent(
    state: UsuarioUiState,
    onNombreChange: (String) -> Unit,
    onGuardar: () -> Unit,
    onContinue: () -> Unit
) {
    LaunchedEffect(state.esUsuarioGuardado) {
        if (state.esUsuarioGuardado) {
            onContinue()
        }
    }

    if (!state.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido a Antillas AquaDex",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Tu enciclopedia marina de las antillas",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = state.nombre,
                onValueChange = onNombreChange,
                label = { Text("¿Cómo te llamas?") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onGuardar,
                modifier = Modifier.fillMaxWidth(),
                enabled = state.nombre.isNotBlank()
            ) {
                Text("Empezar aventura")
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BienvenidaPreview() {
    AntillasAquaDexAppTheme {
        BienvenidaContent(
            state = UsuarioUiState(nombre = "Steven", isLoading = false),
            onNombreChange = {},
            onGuardar = {},
            onContinue = {}
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BienvenidaDarkPreview() {
    AntillasAquaDexAppTheme {
        BienvenidaContent(
            state = UsuarioUiState(nombre = "", isLoading = false),
            onNombreChange = {},
            onGuardar = {},
            onContinue = {}
        )
    }
}
