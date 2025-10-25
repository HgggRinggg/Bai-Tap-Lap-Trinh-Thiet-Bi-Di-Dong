package com.example.appthuvien.model

import androidx.compose.runtime.mutableStateListOf
import com.example.appthuvien.R

data class Book(
    val id: String,
    val title: String,
    var isBorrowed: Boolean = false,
    var borrowedBy: String? = null
)

data class Student(
    val id: String,
    val name: String,
    val borrowedBooks: MutableList<String> = mutableListOf()
)

// ============ REPOSITORY (OOP) ============
class LibraryRepository {
    private val books = mutableStateListOf<Book>()
    private val students = mutableStateListOf<Student>()

    fun addBook(title: String) {
        val id = "B${books.size + 1}"
        books.add(Book(id, title))
    }

    fun addStudent(name: String) {
        val id = "S${students.size + 1}"
        students.add(Student(id, name))
    }

    fun getBooks() = books
    fun getStudents() = students

    fun borrowBook(bookId: String, studentId: String): Boolean {
        val book = books.find { it.id == bookId }
        val student = students.find { it.id == studentId }

        if (book != null && student != null && !book.isBorrowed) {
            book.isBorrowed = true
            book.borrowedBy = student.name
            student.borrowedBooks.add(bookId)
            return true
        }
        return false
    }

    fun returnBook(bookId: String, studentId: String) {
        val book = books.find { it.id == bookId }
        val student = students.find { it.id == studentId }

        if (book != null && student != null) {
            book.isBorrowed = false
            book.borrowedBy = null
            student.borrowedBooks.remove(bookId)
        }
    }

    fun getBorrowedBooks(studentId: String): List<Book> {
        val student = students.find { it.id == studentId } ?: return emptyList()
        return books.filter { it.id in student.borrowedBooks }
    }
}

enum class Destination(
    val route: String,
    val iconRes: Int,
    val label: String,
    val contentDescription: String
) {
    MANAGE("manage", R.drawable.house,
        "Quản Lý", "Go to manage"),

    BOOK("books", R.drawable.file,
        "DS Sách", "Go to booklist"),

    STUDENT("students", R.drawable.user,
        "DS Sinh Viên", "Go to studentlist")
}
