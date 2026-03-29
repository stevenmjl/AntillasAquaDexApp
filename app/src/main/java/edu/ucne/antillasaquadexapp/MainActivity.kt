package edu.ucne.antillasaquadexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.antillasaquadexapp.presentation.navigation.AquaDexNavHost
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AntillasAquaDexAppTheme {
                val navController = rememberNavController()
                AquaDexNavHost(navController = navController)
            }
        }
    }
}
