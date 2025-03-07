package com.example.sdcard

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.Writer

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
        val editTexts = listOf(R.id.editTextText, R.id.editTextText2, R.id.editTextText3)
        val et1 = findViewById<EditText>(R.id.editTextText)
        val et2 = findViewById<EditText>(R.id.editTextText2)
        val et3 = findViewById<EditText>(R.id.editTextText3)
        val saveButton = findViewById<Button>(R.id.button)
        val loadButton = findViewById<Button>(R.id.button2)

        saveButton.setOnClickListener {
            val file = File(getExternalFilesDir(null), "student.txt")
            try {
                val writer = FileWriter(file)
                writer.write("${et1.text}\n${et2.text}\n${et3.text}")
                writer.close()
            } catch(e: Exception) {
                Toast.makeText(this, "Error: $e", Toast.LENGTH_SHORT).show()
            } finally {
                editTexts.map { findViewById<EditText>(it).text.clear() }
                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
            }
        }
        loadButton.setOnClickListener {
            val file = File(getExternalFilesDir(null), "student.txt")
            val reader = BufferedReader(FileReader(file))
            val rollNo= reader.readLine()
            val name = reader.readLine()
            val cgpa = reader.readLine()
            findViewById<EditText>(R.id.editTextText).setText(rollNo)
            findViewById<EditText>(R.id.editTextText2).setText(name)
            findViewById<EditText>(R.id.editTextText3).setText(cgpa)
            reader.close()
            Toast.makeText(this, "Loaded Successfully", Toast.LENGTH_SHORT).show()
        }

    }
}