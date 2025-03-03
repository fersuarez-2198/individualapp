package com.example.individualapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        val userName = intent.getStringExtra("USER_NAME") ?: ""
        val formattedName = userName.lowercase().replaceFirstChar { it.titlecase() }
        findViewById<TextView>(R.id.textViewWelcome).text = "Bienvenido $formattedName"

        val spinnerFundacion = findViewById<Spinner>(R.id.spinnerFundacion)
        val spinnerBanco = findViewById<Spinner>(R.id.spinnerBanco)
        val editTextMonto = findViewById<EditText>(R.id.editTextMonto)
        val buttonConfirmar = findViewById<Button>(R.id.buttonConfirmar)

        val fundaciones = arrayOf("Fundación Corazones Peludos", "Fundación Huellitas", "Fundación Patitas Felices", "Fundación Peluditos")
        val bancos = arrayOf("Banco 1", "Banco 2", "Banco 3", "Banco 4")

        val adapterFundacion = ArrayAdapter(this, android.R.layout.simple_spinner_item, fundaciones)
        val adapterBanco = ArrayAdapter(this, android.R.layout.simple_spinner_item, bancos)

        adapterFundacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterBanco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFundacion.adapter = adapterFundacion
        spinnerBanco.adapter = adapterBanco

        fun validarCampos() {
            val montoValido = editTextMonto.text.toString().isNotEmpty()
            val fundacionSeleccionada = spinnerFundacion.selectedItem != null
            val bancoSeleccionado = spinnerBanco.selectedItem != null
            buttonConfirmar.isEnabled = montoValido && fundacionSeleccionada && bancoSeleccionado
        }

        editTextMonto.addTextChangedListener { validarCampos() }

        spinnerFundacion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                validarCampos()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinnerBanco.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                validarCampos()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        buttonConfirmar.setOnClickListener {
            val intent = Intent(this, Popup::class.java).apply {
                putExtra("MONTO", editTextMonto.text.toString())
                putExtra("FUNDACION", spinnerFundacion.selectedItem.toString())
                putExtra("BANCO", spinnerBanco.selectedItem.toString())
            }
            startActivity(intent)
        }
    }
}
