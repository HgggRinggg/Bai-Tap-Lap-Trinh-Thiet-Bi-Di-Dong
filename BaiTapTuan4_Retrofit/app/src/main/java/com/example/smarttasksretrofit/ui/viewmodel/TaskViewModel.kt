package com.example.smarttasksretrofit.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttasksretrofit.data.api.RetrofitInstance
import com.example.smarttasksretrofit.data.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getAllTasks()
                if (response.isSuccessful) {
                    val taskResponse = response.body()
                    _tasks.value = taskResponse?.data ?: emptyList()
                } else {
                    _tasks.value = emptyList()
                }
            } catch (e: Exception) {
                _tasks.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadTaskDetail(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val existingTask = _tasks.value.find { it.id == id }
                if (existingTask != null) {
                    _selectedTask.value = existingTask
                } else {
                    val response = RetrofitInstance.api.getAllTasks()
                    if (response.isSuccessful) {
                        val list = response.body()?.data ?: emptyList()
                        _tasks.value = list
                        _selectedTask.value = list.find { it.id == id }
                    }
                }
            } catch (e: Exception) {
                _selectedTask.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteTask(
        id: Int,
        onDeleted: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.deleteTask(id)
                if (response.isSuccessful) {
                    _tasks.value = _tasks.value.filterNot { it.id == id }
                    onDeleted()
                    loadTasks()
                }
            } catch (e: Exception) { }
        }
    }
}
