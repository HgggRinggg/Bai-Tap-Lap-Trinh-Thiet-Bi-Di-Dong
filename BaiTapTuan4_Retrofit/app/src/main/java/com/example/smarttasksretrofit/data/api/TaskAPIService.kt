package com.example.smarttasksretrofit.data.api

import com.example.smarttasksretrofit.data.model.Task
import com.example.smarttasksretrofit.data.model.TaskResponse
import retrofit2.Response
import retrofit2.http.*

interface TaskAPIService {
    @GET("tasks")
    suspend fun getAllTasks(): Response<TaskResponse>

    @GET("task/{id}")
    suspend fun getTaskById(@Path("id") id: Int): Response<Task>

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>
}
