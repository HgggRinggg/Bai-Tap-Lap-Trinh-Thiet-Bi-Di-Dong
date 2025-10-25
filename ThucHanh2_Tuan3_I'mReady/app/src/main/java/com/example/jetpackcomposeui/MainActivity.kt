package com.example.jetpackcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeui.ui.theme.Blue
import com.example.jetpackcomposeui.ui.theme.JetpackComposeUITheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeUITheme {
                JetpackComposeUI()
            }
        }
    }
}

@Composable
fun JetpackComposeUI()
{
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier
                .size(216.dp, 233.dp)
        )

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            "Jetpack Compose",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(128.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .width(250.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Blue)
        ) {
            Text(
                "I'm ready",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Normal)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetpackComposeUIPreview()
{
    JetpackComposeUITheme {
        JetpackComposeUI()
    }
}