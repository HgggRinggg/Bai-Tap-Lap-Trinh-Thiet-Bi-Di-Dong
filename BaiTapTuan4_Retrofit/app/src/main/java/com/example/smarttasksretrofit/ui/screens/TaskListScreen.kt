package com.example.smarttasksretrofit.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smarttasksretrofit.data.model.Task
import com.example.smarttasksretrofit.ui.viewmodel.TaskViewModel
import com.example.smarttasksretrofit.R

@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadTasks() }

    Scaffold(
        modifier = Modifier.padding(24.dp),
        topBar = { TopBarListScreen() },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        if (tasks.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.img),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        tint = Color(0xFF9E9E9E)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        "No Tasks Yet!",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFF424242)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Stay productive—add something to do",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF757575),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(tasks.size) { i ->
                    TaskCard(task = tasks[i], onClick = onTaskClick)
                }
            }
        }
    }
}

@Composable
fun TaskCard(task: Task, onClick: (Int) -> Unit) {
    val bgColor = when (task.category) {
        "Work" -> Color(0xFFFFE0E7)
        "Fitness" -> Color(0xFFC8E6C9)
        else -> Color(0xFFB3E5FC)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { onClick(task.id) },
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.status == "In Progress",
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFFEC407A),
                    uncheckedColor = Color(0xFF9E9E9E)
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = "Status: ${task.status}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF757575)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = task.time ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF757575)
                    )
                }
            }
        }
    }
}