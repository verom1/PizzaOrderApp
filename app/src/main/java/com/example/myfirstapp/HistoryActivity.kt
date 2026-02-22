package com.example.myfirstapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val txtHistory = findViewById<TextView>(R.id.txt_history_content)

        try {
            val content = openFileInput("flower_orders.txt").bufferedReader().use { it.readText() }
            txtHistory.text = if (content.isEmpty()) "Сховище порожнє" else content
        } catch (e: Exception) {
            txtHistory.text = "Дані відсутні (сховище порожнє)"
        }
    }
}