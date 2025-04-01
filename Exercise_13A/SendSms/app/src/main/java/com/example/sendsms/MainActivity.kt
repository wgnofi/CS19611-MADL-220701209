package com.example.sendsms

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val SMS_PERMISSION_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val phoneNumber = findViewById<EditText>(R.id.etNumber)
        val message = findViewById<EditText>(R.id.etMessage)
        val smsButton = findViewById<Button>(R.id.btSms)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), SMS_PERMISSION_CODE)
        }
        smsButton.setOnClickListener {
            val phoneNo = phoneNumber.text.toString()
            val m = message.text.toString()
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, m, null, null)
            Toast.makeText(this, "SMS Sent Successfully...!", Toast.LENGTH_LONG).show()
        }
    }
}