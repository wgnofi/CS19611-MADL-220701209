package com.example.emailapp

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.et_email)
        val subject = findViewById<EditText>(R.id.et_subject)
        val mailText = findViewById<EditText>(R.id.et_text)
        val sendButton = findViewById<Button>(R.id.bt_email)

        sendButton.setOnClickListener {
            val (em, sub, tex) = listOf(email, subject, mailText).map { it.text.toString() }
            val intent = Intent(ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(em))
            intent.putExtra(Intent.EXTRA_SUBJECT, sub)
            intent.putExtra(Intent.EXTRA_TEXT, tex)
            intent.type = "message/rfc822"
            startActivity(Intent.createChooser(intent, "Choose a mail client..."))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}