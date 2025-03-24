package com.example.simple_calci

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
        val addButton = findViewById<Button>(R.id.bt1)
        val subButton = findViewById<Button>(R.id.bt2)
        val mulButton = findViewById<Button>(R.id.bt3)
        val divButton = findViewById<Button>(R.id.bt4)
        val calculation = findViewById<TextView>(R.id.textView)
        val et1 = findViewById<EditText>(R.id.et1)
        val et2 = findViewById<EditText>(R.id.et2)
        addButton.setOnClickListener {
            if (checkIfEmpty(et1, et2)) {
                Toast.makeText(this, "Enter some number", Toast.LENGTH_SHORT).show()
            } else {
                val res = et1.text.toString().toInt() + et2.text.toString().toInt()
                calculation.text = res.toString()
            }
        }
        subButton.setOnClickListener {
            if (checkIfEmpty(et1, et2)) {
                Toast.makeText(this, "Enter some number", Toast.LENGTH_SHORT).show()
            } else {
                val res = et1.text.toString().toInt() - et2.text.toString().toInt()
                calculation.text = res.toString()
            }
        }
        mulButton.setOnClickListener {
            if (checkIfEmpty(et1, et2)) {
                Toast.makeText(this, "Enter some number", Toast.LENGTH_SHORT).show()
            } else {
                val res = et1.text.toString().toInt() * et2.text.toString().toInt()
                calculation.text = res.toString()
            }
        }
        divButton.setOnClickListener {
            if (checkIfEmpty(et1, et2)) {
                Toast.makeText(this, "Enter some number", Toast.LENGTH_SHORT).show()
            } else {
                val res = et1.text.toString().toInt() / et2.text.toString().toInt()
                calculation.text = res.toString()
            }
        }
    }
}

fun checkIfEmpty(f1: EditText, f2: EditText) = f1.text.isEmpty() || f2.text.isEmpty()
