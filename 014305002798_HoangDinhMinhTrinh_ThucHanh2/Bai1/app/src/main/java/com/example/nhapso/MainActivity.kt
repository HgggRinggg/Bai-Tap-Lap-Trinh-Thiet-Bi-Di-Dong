package com.example.nhapso

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity()
{
    private lateinit var nhapSo: TextInputEditText
    private lateinit var baoLoi: TextView
    private lateinit var danhSach: LinearLayout
    private lateinit var oMau: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nhapSo = findViewById(R.id.etNhap)
        baoLoi = findViewById(R.id.tvLoi)
        danhSach = findViewById(R.id.llDS)
        oMau = findViewById(R.id.btnMau)
    }

    fun xuLyTao(view: View)
    {
        baoLoi.visibility = View.GONE
        danhSach.removeAllViews()

        val chuNhap = nhapSo.text.toString().trim()
        val soNhap = chuNhap.toIntOrNull()

        if (chuNhap.isEmpty())
            return

        if (soNhap == null || soNhap <= 0)
        {
            baoLoi.visibility = View.VISIBLE
            return
        }

        for (i in 1..soNhap)
        {
            val oMoi = Button(this)

            oMoi.layoutParams = oMau.layoutParams
            oMoi.text = "$i"
            oMoi.setBackgroundResource(R.drawable.rex_box)
            oMoi.setTextColor(oMau.textColors)
            oMoi.typeface = oMau.typeface

            danhSach.addView(oMoi)
        }
    }
}
