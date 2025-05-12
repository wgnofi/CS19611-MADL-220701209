package com.example.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var pendingIntent: PendingIntent
    private lateinit var alarmManager: AlarmManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val alarmTimePicker: TimePicker = findViewById(R.id.time)
        val btSetAlarm = findViewById<Button>(R.id.button)
        val btCancel = findViewById<Button>(R.id.button2)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btSetAlarm.setOnClickListener {
            Toast.makeText(this, "Alarm ON!", Toast.LENGTH_SHORT).show()

            val calender: Calendar = Calendar.getInstance()
            calender.set(Calendar.HOUR_OF_DAY, alarmTimePicker.hour)
            calender.set(Calendar.MINUTE, alarmTimePicker.minute)

            val intent = Intent(this, AlarmReceiver::class.java)

            pendingIntent = PendingIntent.getBroadcast(this.applicationContext, 2, intent,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            val time: Long = calender.timeInMillis - (calender.timeInMillis % 60000)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent)
            btCancel.setOnClickListener {
                alarmManager.cancel(pendingIntent)
                Toast.makeText(this, "ALARM OFF!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}