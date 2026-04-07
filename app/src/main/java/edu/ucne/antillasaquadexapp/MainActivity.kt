package edu.ucne.antillasaquadexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.antillasaquadexapp.presentation.navigation.AquaDexNavHost
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme
import edu.ucne.antillasaquadexapp.util.AudioManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AntillasAquaDexAppTheme {
                val navController = rememberNavController()
                AquaDexNavHost(
                    navController = navController,
                    audioManager = audioManager
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        audioManager.resumeMusic()
    }

    override fun onPause() {
        super.onPause()
        audioManager.pauseMusic()
    }
}
