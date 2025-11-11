package com.example.smarttasksretrofit.data.model

data class TaskResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)
