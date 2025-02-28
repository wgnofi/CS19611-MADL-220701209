package com.example.tempconverter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val result = findViewById<TextView>(R.id.result)
        val btCelsius = findViewById<Button>(R.id.bt_celsius)
        val btFahrenheit = findViewById<Button>(R.id.bt_fahrenheit)
        val editText = findViewById<EditText>(R.id.et_temp)
        btCelsius.setOnClickListener {
            result.text = convertToCelsius(editText.text.toString())
        }
        btFahrenheit.setOnClickListener {
            result.text = convertToFahrenheit(editText.text.toString())
        }
    }
}
fun convertToCelsius(f:String):String {
    val fa = f.toFloat()
    val c = (fa - 32) * (5/9)
    return "$c C"
}
fun convertToFahrenheit(c:String):String {
    val ce = c.toFloat()
    val f = (ce * (9/5)) + 32
    return "$f F"
}