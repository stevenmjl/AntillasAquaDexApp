package edu.ucne.antillasaquadexapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AquaDexTopBar(
    title: String,
    onNavigateBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val colorPrimario = MaterialTheme.colorScheme.primary
    val colorContenedor = MaterialTheme.colorScheme.primaryContainer
    val colorTexto = MaterialTheme.colorScheme.onPrimaryContainer

    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                // 1. Fondo con degradado oceánico
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorContenedor,
                            colorContenedor.copy(alpha = 0.85f)
                        )
                    )
                )

                // 2. Burbujas decorativas estáticas
                val bubbleColor = colorPrimario.copy(alpha = 0.12f)
                drawCircle(bubbleColor, radius = 18f, center = Offset(size.width * 0.12f, size.height * 0.35f))
                drawCircle(bubbleColor, radius = 10f, center = Offset(size.width * 0.18f, size.height * 0.75f))
                drawCircle(bubbleColor, radius = 14f, center = Offset(size.width * 0.82f, size.height * 0.45f))
                drawCircle(bubbleColor, radius = 22f, center = Offset(size.width * 0.90f, size.height * 0.85f))

                // 3. Dibujar la línea de superficie con ondas
                val wavePath = Path().apply {
                    val waveHeight = 6f
                    moveTo(0f, size.height)
                    for (i in 0..size.width.toInt() step 15) {
                        val y = size.height - (waveHeight * Math.sin(i.toDouble() / 35.0)).toFloat()
                        lineTo(i.toFloat(), y)
                    }
                    lineTo(size.width, size.height)
                }
                drawPath(
                    path = wavePath,
                    color = colorPrimario.copy(alpha = 0.25f),
                    style = Fill
                )
            },
        windowInsets = WindowInsets(0, 0, 0, 0),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.offset(y = (-4).dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Waves,
                    contentDescription = null,
                    tint = colorTexto.copy(alpha = 0.5f),
                    modifier = Modifier.size(20.dp)
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.titleLarge,
                    color = colorTexto
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Icon(
                    imageVector = Icons.Default.Waves,
                    contentDescription = null,
                    tint = colorTexto.copy(alpha = 0.5f),
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        navigationIcon = {
            if (onNavigateBack != null) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.offset(y = (-4).dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = colorTexto
                    )
                }
            }
        },
        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.offset(y = (-4).dp)
            ) {
                actions()
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent, // Dibujamos el fondo nosotros
            titleContentColor = colorTexto,
            navigationIconContentColor = colorTexto,
            actionIconContentColor = colorTexto
        )
    )
}
