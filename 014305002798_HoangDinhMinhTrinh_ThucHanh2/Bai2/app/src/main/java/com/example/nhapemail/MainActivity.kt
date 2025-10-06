package com.example.nhapemail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity()
{
    private lateinit var nhapDuLieu: TextInputEditText
    private lateinit var baoLoi: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        nhapDuLieu = findViewById(R.id.etNhap)
        baoLoi = findViewById(R.id.tvLoi)
    }

    fun kiemTra(view: View)
    {
        baoLoi.visibility = View.GONE

        val email = nhapDuLieu.text.toString().trim()

        baoLoi.text = when
        {
            email.isEmpty() || email == null -> "Email không hợp lệ"
            !email.contains("@") -> "Email không đúng định dạng"
            else -> "Email hợp lệ"
        }

        baoLoi.visibility = View.VISIBLE
    }
}