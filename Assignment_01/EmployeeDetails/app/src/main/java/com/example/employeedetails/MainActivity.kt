package com.example.employeedetails

import android.content.SharedPreferences.Editor
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
        val empID = findViewById<EditText>(R.id.et1)
        val empName = findViewById<EditText>(R.id.et2)
        val empSalary = findViewById<EditText>(R.id.et3)
        val loadedText = findViewById<TextView>(R.id.tv)
        val saveButton = findViewById<Button>(R.id.button)
        val loadButton = findViewById<Button>(R.id.button2)
        val l = listOf(empID, empName, empSalary)
        val saveAble = l.map { it.text }

        saveButton.setOnClickListener {
            val file = File(getExternalFilesDir(null), "emp.txt")
            try {
                val writer = FileWriter(file)
                writer.write(saveAble.joinToString("\n"))
                writer.close()
            } catch(e: Exception) {
                Toast.makeText(this, "Error: $e", Toast.LENGTH_SHORT).show()
            }
            finally {
                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
            }
            loadedText.text = saveAble.joinToString("\n")
        }
        loadButton.setOnClickListener {
            val file = File(getExternalFilesDir(null), "emp.txt")
            val reader = BufferedReader(FileReader(file))
            val final = buildString {
                for (i in 1..3) {
                    append(reader.readLine())
                    append("\n")
                }
            }
            reader.close()
            loadedText.text = final
        }
    }
}