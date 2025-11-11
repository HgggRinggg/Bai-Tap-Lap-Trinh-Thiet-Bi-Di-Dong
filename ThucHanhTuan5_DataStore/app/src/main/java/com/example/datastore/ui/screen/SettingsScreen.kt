package com.example.datastore.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastore.data.ThemePreferences
import com.example.datastore.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    themePreferences: ThemePreferences,
    savedTheme: Int,
    onThemeChange: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    var tempTheme by remember { mutableIntStateOf(savedTheme) }

    LaunchedEffect(savedTheme) {
        tempTheme = savedTheme
    }

    val themeOptions = listOf(
        ThemePreferences.THEME_BLUE to JordyBlue,
        ThemePreferences.THEME_PINK to CandyVeil,
        ThemePreferences.THEME_DARK to DarkGray
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Setting",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Choosing the right theme sets the tone and personality of your app",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
        )

        Spacer(modifier = Modifier.height(48.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            themeOptions.forEach { (themeId, color) ->
                ThemeOptionBox(
                    color = color,
                    isSelected = tempTheme == themeId,
                    borderColor = MaterialTheme.colorScheme.outline,
                    onClick = {
                        tempTheme = themeId
                        onThemeChange(themeId)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                scope.launch {
                    themePreferences.saveTheme(tempTheme)
                }
            },
            modifier = Modifier
                .width(200.dp)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Apply",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun ThemeOptionBox(
    color: Color,
    isSelected: Boolean,
    borderColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = if (isSelected) 4.dp else 0.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
    )
}

