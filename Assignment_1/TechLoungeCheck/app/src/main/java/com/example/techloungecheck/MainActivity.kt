package com.example.techloungecheck

import android.os.Bundle
import android.widget.Button
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
        val checkInButton = findViewById<Button>(R.id.bt_checkIn)
        val checkOutButton = findViewById<Button>(R.id.bt_checkOut)
        val countText = findViewById<TextView>(R.id.s_count)
        var count = 0
        checkInButton.setOnClickListener {
            count++
            countText.text = String.format(count.toString())
        }
        checkOutButton.setOnClickListener {
            if (count>0) count-- else count = 0
            countText.text = String.format(count.toString())
        }
    }
}