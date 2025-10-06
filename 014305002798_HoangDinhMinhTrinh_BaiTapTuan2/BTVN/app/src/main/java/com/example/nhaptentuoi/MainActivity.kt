package com.example.nhaptentuoi

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
    private lateinit var nhapTuoi: TextInputEditText
    private lateinit var thongBao: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        nhapTuoi = findViewById(R.id.etTuoi)
        thongBao = findViewById(R.id.tvKetQua)
    }

    fun kiemTra(view: View)
    {
        thongBao.visibility = View.GONE

        val tuoiText = nhapTuoi.text.toString().trim()
        val tuoi = tuoiText.toInt()

        thongBao.text = when
        {
            tuoi > 65 -> "Người già"
            tuoi > 6 && tuoi <= 65 -> "Người lớn"
            tuoi >= 2 && tuoi <= 6 -> "Trẻ em"
            tuoi < 2 -> "Em bé"
            else -> "Lỗi dữ liệu! Vui lòng kiểm tra lại"
        }

        thongBao.visibility = View.VISIBLE
    }
}