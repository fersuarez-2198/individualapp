package com.example.individualapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Popup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_popup)

        // Obtener los datos enviados desde WelcomeActivity
        val monto = intent.getStringExtra("MONTO")
        val fundacion = intent.getStringExtra("FUNDACION")
        val banco = intent.getStringExtra("BANCO")

        // Referencias a los elementos de la UI
        val textViewMensaje = findViewById<TextView>(R.id.textViewMensaje)
        val buttonAceptar = findViewById<Button>(R.id.buttonAceptar)

        // Mostrar el mensaje con los datos de la donación
        textViewMensaje.text = "Donación de $monto COP a $fundacion mediante $banco confirmada. ¡Gracias por ayudar a tantos peluditos 🐕🐩🐕‍🦺🦮🐶!"

        // Cerrar la actividad al hacer clic en "Aceptar"
        buttonAceptar.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
