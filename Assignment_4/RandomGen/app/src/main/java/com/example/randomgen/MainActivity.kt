package com.example.randomgen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

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
        val button = findViewById<Button>(R.id.bt_gen)
        val editText = findViewById<EditText>(R.id.editTextText)

        button.setOnClickListener {
            val flList = editText.text.toString().split("-")
            res.text = genRandomNumber(flList[0].toInt(), flList[1].toInt())
        }

    }
    private fun genRandomNumber(first: Int, second: Int): String {
        return "Random number between $first-$second: ${(first..second).random()}"
    }
}