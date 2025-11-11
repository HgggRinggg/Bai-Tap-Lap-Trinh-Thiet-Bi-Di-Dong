package com.example.smarttasksretrofit.data.model

import com.google.gson.annotations.SerializedName

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val category: String?,
    val priority: String?,
    @SerializedName("dueDate") val time: String?,
    val subtasks: List<Subtask>?,
    val attachments: List<Attachment>?
)

data class Subtask(
    val id: Int?,
    val title: String,
    @SerializedName("isCompleted") val completed: Boolean
)

data class Attachment(
    val id: Int?,
    @SerializedName("fileName") val name: String,
    @SerializedName("fileUrl") val url: String?
)
