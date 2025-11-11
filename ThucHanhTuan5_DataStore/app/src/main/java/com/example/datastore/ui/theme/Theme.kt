package com.example.datastore.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.datastore.data.ThemePreferences

private val WhiteColors = darkColorScheme(
    primary = Black,
    outline = Black,
    background = White,
    onBackground = Black,
    onPrimary = White
)

private val BlackColors = lightColorScheme(
    primary = White,
    outline = White,
    background = Black,
    onBackground = White,
    onPrimary = Black
)

private val PinkColors = lightColorScheme(
    primary = CherryRed,
    outline = CherryRed,
    background = CandyVeil,
    onBackground = CherryRed,
    onPrimary = CandyVeil
)

private val BlueColors = lightColorScheme(
    primary = DarkBlue,
    outline = DarkBlue,
    background = JordyBlue,
    onBackground = DarkBlue,
    onPrimary = JordyBlue
)

@Composable
fun DataStoreTheme(
    themePreferences: ThemePreferences,
    selectedTheme: Int,
    content: @Composable () -> Unit
) {
    val colorScheme: ColorScheme = when (selectedTheme)
    {
        ThemePreferences.THEME_DARK -> BlackColors
        ThemePreferences.THEME_PINK -> PinkColors
        ThemePreferences.THEME_BLUE -> BlueColors
        else -> WhiteColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}