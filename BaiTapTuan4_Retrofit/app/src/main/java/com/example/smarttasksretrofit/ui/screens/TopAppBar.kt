package com.example.smarttasksretrofit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttasksretrofit.R

@Composable
fun TopBarListScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.uth),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("SmartTasks", style = MaterialTheme.typography.titleLarge)
                    Text(
                        "A simple and efficient to-do app",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            IconButton(onClick = {})
            {
                Icon(
                    painter = painterResource(R.drawable.noti),
                    contentDescription = "Notifications"
                )
            }
        }
    }
}

@Composable
fun TopBarDetailScreen(
    onBack: () -> Unit,
    onDelete: () -> Unit
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .background(
                        color = Color(0xFFB1C9EF),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.back),
                    contentDescription = "Back",
                    tint = Color(0xFF122C4F),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Detail",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp),
                    color = Color(0xFFB1C9EF)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onDelete)
            {
                Icon(
                    painter = painterResource(R.drawable.delete),
                    contentDescription = "Delete Task"
                )
            }
        }
    }
}
