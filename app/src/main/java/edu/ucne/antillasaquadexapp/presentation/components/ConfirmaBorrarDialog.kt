package edu.ucne.antillasaquadexapp.presentation.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@Composable
fun ConfirmaBorrarDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    nombreEspecie: String
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Default.DeleteOutline,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        },
        title = {
            Text(
                text = "Eliminar de favoritos",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = "¿Estás seguro de que deseas eliminar a '$nombreEspecie' de tu lista de favoritos?",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "Eliminar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Cancelar",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        shape = MaterialTheme.shapes.extraLarge,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = AlertDialogDefaults.TonalElevation
    )
}

@Preview(showBackground = true)
@Composable
fun ConfirmaBorrarDialogPreview() {
    AntillasAquaDexAppTheme {
        ConfirmaBorrarDialog(
            onConfirm = {},
            onDismiss = {},
            nombreEspecie = "Manatí del Caribe"
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ConfirmaBorrarDialogDarkPreview() {
    AntillasAquaDexAppTheme {
        ConfirmaBorrarDialog(
            onConfirm = {},
            onDismiss = {},
            nombreEspecie = "Manatí del Caribe"
        )
    }
}