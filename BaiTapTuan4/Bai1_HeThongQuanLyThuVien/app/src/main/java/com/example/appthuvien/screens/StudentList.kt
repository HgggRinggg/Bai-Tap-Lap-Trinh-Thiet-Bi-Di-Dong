package com.example.appthuvien.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appthuvien.model.LibraryRepository
import com.example.appthuvien.ui.theme.AppThuVienTheme
import com.example.appthuvien.ui.theme.DarkBlue
import com.example.appthuvien.ui.theme.WhiteBlue

@Composable
fun StudentListScreen(repository: LibraryRepository) {
    var studentName by remember { mutableStateOf("") }
    val students = repository.getStudents()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Danh Sách Sinh viên",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = DarkBlue
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = studentName,
            onValueChange = { studentName = it },
            label = { Text("Tên sinh viên") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(students) { student ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = WhiteBlue
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(student.name, fontWeight = FontWeight.Bold, color = DarkBlue)
                        Text("Đang mượn: ${student.borrowedBooks.size} sách", color = DarkBlue)
                    }
                }
            }
        }

        Button(
            onClick = {
                if (studentName.isNotBlank()) {
                    repository.addStudent(studentName)
                    studentName = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp, 16.dp),
            colors = ButtonDefaults.buttonColors(DarkBlue)
        ) {
            Text(
                "Thêm",
                color = WhiteBlue,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentListPreview() {
    AppThuVienTheme {
        val repository = LibraryRepository().apply {
            addStudent("Nguyễn Văn A")
            addStudent("Trần Thị B")
        }
        StudentListScreen(repository)
    }
}