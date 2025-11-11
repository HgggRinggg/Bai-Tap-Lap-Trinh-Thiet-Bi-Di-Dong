package com.example.smarttasksretrofit.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smarttasksretrofit.ui.viewmodel.TaskViewModel
import com.example.smarttasksretrofit.R

@Composable
fun TaskDetailScreen(
    viewModel: TaskViewModel,
    taskId: Int,
    onBack: () -> Unit
) {
    val task by viewModel.selectedTask.collectAsState()

    LaunchedEffect(taskId) { viewModel.loadTaskDetail(taskId) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        topBar = {
            TopBarDetailScreen(
                onBack = onBack,
                onDelete = { viewModel.deleteTask(taskId) { onBack() } }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            task?.let { t ->
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(t.title, style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.height(24.dp))

                    Row {
                        Badge(containerColor = Color(0xFFE91E63)) { Text(t.category ?: "General") }
                        Spacer(Modifier.width(8.dp))
                        Badge(containerColor = Color(0xFFFF9800)) { Text(t.status) }
                        Spacer(Modifier.width(8.dp))
                        Badge(containerColor = Color(0xFF4CAF50)) { Text(t.priority ?: "Medium") }
                    }

                    Spacer(Modifier.height(24.dp))
                    Text("Subtasks", style = MaterialTheme.typography.titleMedium)

                    t.subtasks?.forEach { sub ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = sub.completed, onCheckedChange = {})
                            Spacer(Modifier.width(8.dp))
                            Text(sub.title, style = MaterialTheme.typography.bodyMedium)
                        }
                    } ?: Text("No subtasks", color = Color.Gray)

                    Spacer(Modifier.height(24.dp))
                    Text("Attachments", style = MaterialTheme.typography.titleMedium)

                    t.attachments?.forEach { att ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.attachment),
                                    contentDescription = null,
                                    tint = Color(0xFF757575)
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(att.name, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    } ?: Text("No attachments", color = Color.Gray)
                }
            } ?: CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun InfoChip(label: String, value: String) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("$label: ", style = MaterialTheme.typography.labelMedium)
            Text(value, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
