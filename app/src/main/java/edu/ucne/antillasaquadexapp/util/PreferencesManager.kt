package edu.ucne.antillasaquadexapp.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
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

    val isInitialSyncCompleted: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_INITIAL_SYNC_COMPLETED] ?: false
        }

    suspend fun setInitialSyncCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_INITIAL_SYNC_COMPLETED] = completed
        }
    }
}
