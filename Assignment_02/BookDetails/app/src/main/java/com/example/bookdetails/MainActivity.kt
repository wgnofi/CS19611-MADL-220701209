package com.example.bookdetails

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.Reader

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
        val resources = listOf(R.id.et1, R.id.et2, R.id.et3, R.id.et4).map { findViewById<EditText>(it).text }
        val saveButton = findViewById<Button>(R.id.button)
        val loadButton = findViewById<Button>(R.id.button2)
        val textView = findViewById<TextView>(R.id.textView)
        saveButton.setOnClickListener {
            try {
                val file = File(getExternalFilesDir(null), "book.txt")
                val writer = FileWriter(file)
                writer.write(resources.joinToString("\n"))
                writer.close()
            } catch(e: Exception) {
                Toast.makeText(this, "Error: $e", Toast.LENGTH_SHORT).show()
            } finally {
                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
            }
        }
        loadButton.setOnClickListener {
            val file = File(getExternalFilesDir(null), "book.txt")
            val reader = BufferedReader(FileReader(file))
            val final = buildString { for (i in 1..4) { append(reader.readLine());append("\n")} }
            textView.text = final
        }
    }
}