package com.example.appthuvien.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appthuvien.R
import com.example.appthuvien.model.Book
import com.example.appthuvien.model.LibraryRepository
import com.example.appthuvien.model.Student
import com.example.appthuvien.ui.theme.*

@Composable
fun ManagementScreen(repository: LibraryRepository) {
    var selectedStudent by remember { mutableStateOf<Student?>(null) }
    val students = repository.getStudents()
    val books = repository.getBooks()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Hệ Thống\nQuản Lý Thư Viện",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = DarkBlue
        )

        Text(
            "Sinh viên",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            style = MaterialTheme.typography.titleSmall,
            color = DarkBlue
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = selectedStudent?.name ?: "",
                onValueChange = {},
                placeholder = {
                    Text(
                        "Họ tên sinh viên",
                        color = DarkBlue
                    )
                },

                textStyle = TextStyle(
                    color = DarkBlue,
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                ),

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = LightBlue,
                    unfocusedBorderColor = DarkBlue
                )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    val index = students.indexOf(selectedStudent)
                    selectedStudent = students.getOrNull((index + 1) % students.size)
                },

                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Blue),
                modifier = Modifier
                    .height(60.dp)
            ) {
                Text(
                    "Thay Đổi",
                    style = MaterialTheme.typography.titleSmall,
                    color = WhiteBlue
                )
            }
        }

        Text(
            "Danh sách sách",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            style = MaterialTheme.typography.titleSmall,
            color = DarkBlue
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .weight(1f),
            color = LightBlue,
            shape = RoundedCornerShape(16.dp)
        ) {
            if (selectedStudent != null) {
                val borrowedBooks = repository.getBorrowedBooks(selectedStudent!!.id)
                if (borrowedBooks.isEmpty()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(22.dp)
                    ) {
                        Text(
                            "Bạn chưa mượn quyển sách nào.\n\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                            color = DarkBlue,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                else
                {
                    LazyColumn(modifier = Modifier.padding(16.dp))
                    {
                        items(borrowedBooks) { book ->
                            BookItemStyled(book)
                            {
                                repository.returnBook(
                                    book.id,
                                    selectedStudent!!.id
                                )
                            }
                        }
                    }
                }
            }

            else
            {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(22.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        "Chưa chọn sinh viên",
                        color = DarkBlue,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        var selectedBook by remember { mutableStateOf<Book?>(null) }
        var expanded by remember { mutableStateOf(false) }

        Text(
            "Chọn sách để mượn",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            style = MaterialTheme.typography.titleSmall,
            color = DarkBlue
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            OutlinedTextField(
                value = selectedBook?.title ?: "",
                onValueChange = {},
                placeholder = {
                    Text(
                        "Chọn sách",
                        color = DarkBlue.copy(alpha = 0.7f)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                trailingIcon = {
                    IconButton(onClick = { expanded = true })
                    {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Chọn sách"
                        )
                    }
                },

                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = LightBlue,
                    unfocusedBorderColor = DarkBlue
                )
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(DarkBlue)
            ) {
                books.filter { !it.isBorrowed }.forEach { book ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                book.title,
                                color = WhiteBlue,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        
                        onClick = {
                            selectedBook = book
                            expanded = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                if (selectedStudent != null && selectedBook != null) {
                    repository.borrowBook(selectedBook!!.id, selectedStudent!!.id)
                    selectedBook = null
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp, 16.dp),
            colors = ButtonDefaults.buttonColors(Blue),
            shape = RoundedCornerShape(16.dp),
            enabled = selectedStudent != null && selectedBook != null
        ) {
            Text(
                "Thêm",
                color = DarkBlue,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun BookItemStyled(book: Book, onReturn: () -> Unit)

{
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = WhiteBlue,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                book.title,
                color = DarkBlue,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f),
            )

            IconButton(onClick = onReturn
            ) {
                Icon(
                    painter = painterResource(R.drawable.xmark),
                    contentDescription = "Close",
                    modifier = Modifier.size(24.dp),
                    tint = Blue
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ManagementPreview() {
    AppThuVienTheme {
        val repository = LibraryRepository().apply {
            addStudent("Nguyễn Văn A")
            addStudent("Trần Thị B")
            addBook("Lập trình Kotlin cơ bản")
            addBook("Android với Jetpack Compose")
        }
        ManagementScreen(repository)
    }
}