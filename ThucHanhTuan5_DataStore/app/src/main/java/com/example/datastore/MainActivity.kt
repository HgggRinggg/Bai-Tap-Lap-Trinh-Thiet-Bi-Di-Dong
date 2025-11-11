package com.example.datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Applier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.datastore.data.ThemePreferences
import com.example.datastore.ui.screen.SettingsScreen
import com.example.datastore.ui.theme.DataStoreTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val themePreferences = ThemePreferences(this)

        setContent {
            val savedTheme by themePreferences.themeFlow
                .collectAsState(initial = ThemePreferences.THEME_LIGHT)
            var currentTheme by remember { mutableIntStateOf(savedTheme) }

            LaunchedEffect(savedTheme) {
                currentTheme = savedTheme
            }

            DataStoreTheme(
                themePreferences = themePreferences,
                selectedTheme = currentTheme
            ) {
                SettingsScreen(
                    themePreferences = themePreferences,
                    savedTheme = savedTheme,
                    onThemeChange = { newTheme ->
                        currentTheme = newTheme
                    }
                )
            }
        }
    }
}
