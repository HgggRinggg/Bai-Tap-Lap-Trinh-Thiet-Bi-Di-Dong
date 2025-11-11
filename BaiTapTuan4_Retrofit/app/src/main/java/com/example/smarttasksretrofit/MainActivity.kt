package com.example.smarttasksretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smarttasksretrofit.ui.screens.TaskDetailScreen
import com.example.smarttasksretrofit.ui.screens.TaskListScreen
import com.example.smarttasksretrofit.ui.theme.SmartTasksRetrofitTheme
import com.example.smarttasksretrofit.ui.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartTasksRetrofitTheme {
                val viewModel: TaskViewModel = viewModel()
                var selectedTaskId by remember { mutableStateOf<Int?>(null) }

                if (selectedTaskId == null) {
                    TaskListScreen(viewModel) { id ->
                        selectedTaskId = id
                    }
                } else {
                    TaskDetailScreen(viewModel, selectedTaskId!!) {
                        selectedTaskId = null
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartTasksRetrofitTheme {

    }
}