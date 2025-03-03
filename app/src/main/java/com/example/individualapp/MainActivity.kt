package com.example.individualapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editTextUser = findViewById<EditText>(R.id.editTextText)
        val editTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val buttonLogin = findViewById<Button>(R.id.button)

        buttonLogin.isEnabled = false  // Deshabilitar el botón al inicio

        // Función para validar si los campos están llenos
        fun validarCampos() {
            buttonLogin.isEnabled = editTextUser.text.isNotEmpty() && editTextPassword.text.isNotEmpty()
        }

        // Agregar listeners a los EditText
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { validarCampos() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        editTextUser.addTextChangedListener(textWatcher)
        editTextPassword.addTextChangedListener(textWatcher)

        // Evento al hacer clic en el botón
        buttonLogin.setOnClickListener {
            val usuario = editTextUser.text.toString()
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("USER_NAME", usuario)
            startActivity(intent)
        }

        // Ajustar insets para pantalla completa
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
