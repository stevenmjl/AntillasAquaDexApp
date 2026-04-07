package edu.ucne.antillasaquadexapp.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "settings")

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val IS_INITIAL_SYNC_COMPLETED = booleanPreferencesKey("is_initial_sync_completed")
    private val MUSIC_VOLUME = floatPreferencesKey("music_volume")
    private val PROFILE_PICTURE_URL = stringPreferencesKey("profile_picture_url")

    val isInitialSyncCompleted: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_INITIAL_SYNC_COMPLETED] ?: false
        }

    val musicVolume: Flow<Float> = context.dataStore.data
        .map { preferences ->
            preferences[MUSIC_VOLUME] ?: 0.7f
        }

    val profilePictureUrl: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[PROFILE_PICTURE_URL]
        }

    suspend fun setInitialSyncCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_INITIAL_SYNC_COMPLETED] = completed
        }
    }

    suspend fun setMusicVolume(volume: Float) {
        context.dataStore.edit { preferences ->
            preferences[MUSIC_VOLUME] = volume
        }
    }

    suspend fun setProfilePictureUrl(url: String) {
        context.dataStore.edit { preferences ->
            preferences[PROFILE_PICTURE_URL] = url
        }
    }
}
