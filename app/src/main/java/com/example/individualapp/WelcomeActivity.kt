package com.example.individualapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        val userName = intent.getStringExtra("USER_NAME") ?: ""
        val formattedName = userName.lowercase().replaceFirstChar { it.titlecase() } // Formato correcto

        val textViewWelcome = findViewById<TextView>(R.id.textViewWelcome)
        textViewWelcome.text = "Bienvenido $formattedName"
    }
}
