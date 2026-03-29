package edu.ucne.antillasaquadexapp.presentation.components

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
        title = {
            Text(text = "Eliminar de favoritos")
        },
        text = {
            Text(text = "¿Estás seguro de que deseas eliminar a '$nombreEspecie' de tu lista de favoritos?")
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(text = "Eliminar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        }
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