package com.example.alertdialogbox

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
        val button = findViewById<Button>(R.id.bt_one)
        val editText = findViewById<EditText>(R.id.dialog)
        button.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("MAD Lab")
                .setMessage(editText.text.toString())
                .setPositiveButton("Ok") { dialog, which ->
                    Toast.makeText(this, "You clicked Ok",Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    Toast.makeText(this,"You clicked Cancel", Toast.LENGTH_LONG).show()

                }
                .create()
            alertDialog.show()
        }
    }
}