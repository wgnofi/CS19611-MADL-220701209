package com.example.formvalidation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        val username : EditText = findViewById(R.id.username)
        val password : EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.login)
        val cancelButton: Button = findViewById(R.id.cancel)
        loginButton.setOnClickListener {
            if (username.text.isEmpty() || username.text.trim().contains(' ') || username.text.any { it.isDigit() } || password.text.trim().isEmpty()) {
                val userText = username.text.toString()
                val pass = password.text.toString()
                when {
                    userText.matches(Regex("^[A-Z]$")) -> {
                        Toast.makeText(this, "Email Invalid", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                   !pass.matches(Regex("^[0-9]{4}")) -> {
                       Toast.makeText(this, "Password Invalid", Toast.LENGTH_SHORT).show()
                       return@setOnClickListener
                   }


                    else -> { Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_SHORT).show()}
                }
                return@setOnClickListener
            } else {

                Toast.makeText(this, "You are logged in using your Email!", Toast.LENGTH_SHORT).show()
                //val intent = Intent(this, MainActivity2::class.java)
                //startActivity(intent)
            }

            val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)


        }
        cancelButton.setOnClickListener {
            username.text.clear()
            password.text.clear()
        }

    }
}