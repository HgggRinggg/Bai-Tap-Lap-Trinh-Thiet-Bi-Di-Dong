package com.example.appthuvien.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.appthuvien.ui.theme.LightBlue
import com.example.appthuvien.ui.theme.WhiteBlue

@Composable
fun BookListScreen(repository: LibraryRepository) {
    var bookName by remember { mutableStateOf("") }
    val books = repository.getBooks()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Danh Sách Sách",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = DarkBlue
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = bookName,
            onValueChange = { bookName = it },
            placeholder = {
                Text(
                    "Tên sách",
                    color = DarkBlue.copy(alpha = 0.7f)
                )
            },
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = LightBlue,
                unfocusedBorderColor = DarkBlue
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(books) { book ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    color = if (book.isBorrowed) DarkBlue else LightBlue,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            book.title,
                            fontWeight = FontWeight.Bold,
                            color = if (book.isBorrowed)
                                WhiteBlue
                            else
                                DarkBlue
                        )

                        Text(
                            modifier = Modifier.padding(top = 5.dp),
                            text = if (book.isBorrowed)
                                "Đã mượn: ${book.borrowedBy}"
                            else
                                "Có sẵn",
                            fontSize = 16.sp,
                            color = if (book.isBorrowed)
                                WhiteBlue
                            else
                                DarkBlue
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                if (bookName.isNotBlank())
                {
                    repository.addBook(bookName)
                    bookName = ""
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
fun BookListPreview() {
    AppThuVienTheme {
        val repository = LibraryRepository().apply {
            addBook("Lập trình Kotlin cơ bản")
            addBook("Android với Jetpack Compose")
        }
        BookListScreen(repository)
    }
}