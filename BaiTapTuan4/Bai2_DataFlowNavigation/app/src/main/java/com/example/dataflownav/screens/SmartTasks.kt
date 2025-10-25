package com.example.dataflownav.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dataflownav.R
import com.example.dataflownav.ui.theme.Blue
import com.example.dataflownav.ui.theme.DarkBlue
import com.example.dataflownav.ui.theme.DataFlowNavTheme
import com.example.dataflownav.ui.theme.LightBlue
import com.example.dataflownav.ui.theme.WhiteBlue

@Composable
fun SmartTasks(
    title: String,
    subTitle: String,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .background(WhiteBlue)
            .padding(16.dp, 24.dp, 16.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            if (showBackButton && onBackClick != null)
            {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.back),
                        contentDescription = "Back",
                        tint = LightBlue
                    )
                }
            }
        }

        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "UTH_logo",
            modifier = Modifier.size(120.dp)
        )

        Text(
            "SmartTasks",
            style = MaterialTheme.typography.titleLarge,
            color = Blue
        )

        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = DarkBlue
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            text = subTitle,
            style = MaterialTheme.typography.bodyLarge,
            color = DarkBlue,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun SmartTasksPreview()
{
    DataFlowNavTheme {
        SmartTasks("", "")
    }
}