package com.example.probabilityfunctions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.probabilityfunctions.utils.BinomialHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ejemplos
        val pruebaBinomial = BinomialHelper.probabilidadBinomial(12, 9, 0.5f)
        val pruebaBinomialAcumulada = BinomialHelper.probabilidadBinomialAcumulada(12, 0.5f, 1, 4)
        Toast.makeText(this, pruebaBinomial.toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, pruebaBinomialAcumulada.toString(), Toast.LENGTH_SHORT).show()
    }
}