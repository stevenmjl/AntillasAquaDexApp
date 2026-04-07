package edu.ucne.antillasaquadexapp.util

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferencesManager: PreferencesManager
) {
    private var mediaPlayer: MediaPlayer? = null
    private var currentResId: Int? = null
    private var currentVolume: Float = 0.5f
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        // Observar cambios de volumen desde PreferencesManager
        scope.launch {
            preferencesManager.musicVolume.collectLatest { volume ->
                currentVolume = volume
                mediaPlayer?.setVolume(volume, volume)
            }
        }
    }

    fun playMusic(resId: Int) {
        if (currentResId == resId && mediaPlayer?.isPlaying == true) return

        stopMusic()

        mediaPlayer = MediaPlayer.create(context, resId).apply {
            isLooping = true
            setVolume(currentVolume, currentVolume)
            start()
        }
        currentResId = resId
    }

    fun stopMusic() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        mediaPlayer = null
        currentResId = null
    }

    fun pauseMusic() {
        mediaPlayer?.pause()
    }

    fun resumeMusic() {
        mediaPlayer?.start()
    }
}
