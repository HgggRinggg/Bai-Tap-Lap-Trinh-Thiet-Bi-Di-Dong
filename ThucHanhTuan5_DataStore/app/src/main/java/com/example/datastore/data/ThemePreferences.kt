package com.example.datastore.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_prefs")

class ThemePreferences(private val context: Context)
{
    companion object {
        private val THEME_KEY = intPreferencesKey("selected_theme")

        const val THEME_LIGHT = 0
        const val THEME_DARK = 1
        const val THEME_PINK = 2
        const val THEME_BLUE = 3
    }

    val themeFlow: Flow<Int> = context.dataStore.data
        .map { prefs -> prefs[THEME_KEY] ?: THEME_LIGHT }

    suspend fun saveTheme(theme: Int)
    {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = theme
        }

        Log.d("ThemePreferences", "Theme saved: $theme")
    }
}

