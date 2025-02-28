package com.example.bmi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
        val res = findViewById<TextView>(R.id.res)
        val editHeight = findViewById<EditText>(R.id.et_height)
        val editWeight = findViewById<EditText>(R.id.et_weight)
        val bmiButton = findViewById<Button>(R.id.bt_bmi)
        bmiButton.setOnClickListener {
            if (editWeight.text.toString().isEmpty() || editHeight.text.toString().isEmpty() ) {
                Toast.makeText(this, "Invalid Format! or its Empty", Toast.LENGTH_SHORT).show()
            } else {
                res.text = calculateBMI(heightCm = editHeight.text.toString().toDouble(), weightKg = editWeight.text.toString().toDouble())
            }
        }
    }
}
fun calculateBMI(heightCm: Double, weightKg: Double): String {
    val heightMeters = heightCm / 100
    return "BMI: ${String.format(format = "%.2f",(weightKg / (heightMeters * heightMeters)))}"
}