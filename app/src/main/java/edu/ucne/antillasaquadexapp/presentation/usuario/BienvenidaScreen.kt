package edu.ucne.antillasaquadexapp.presentation.usuario

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.antillasaquadexapp.R
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@Composable
fun BienvenidaScreen(
    onContinue: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onContinue() }
    ) {
        // Imagen de Fondo
        Image(
            painter = painterResource(id = R.drawable.img_titulo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Capa de oscurecimiento suave para legibilidad
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black.copy(alpha = 0.3f)
        ) {}

        // Contenido Central (Título)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Antillas\nAquaDex",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 60.sp
            )
        }

        // Contenido Inferior (Carga e Instrucción)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp)
                .width(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                trackColor = Color.White.copy(alpha = 0.3f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Toque para continuar",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.8f),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TituloScreenPreview() {
    AntillasAquaDexAppTheme {
        BienvenidaScreen(onContinue = {})
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TituloScreenDarkPreview() {
    AntillasAquaDexAppTheme {
        BienvenidaScreen(onContinue = {})
    }
}
