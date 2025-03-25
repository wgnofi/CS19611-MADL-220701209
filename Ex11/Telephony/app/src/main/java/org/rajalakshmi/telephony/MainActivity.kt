package org.rajalakshmi.telephony

import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // specific manager for telephony services
        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        // to check the permission
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_PHONE_STATE), PERMISSION_REQUEST_CODE)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val netOp = findViewById<EditText>(R.id.etOp)
        val country = findViewById<EditText>(R.id.etISO)
        val phoneType = findViewById<EditText>(R.id.etPhoneType)
        val simState = findViewById<EditText>(R.id.etSimState)
        val netType= findViewById<EditText>(R.id.etNetworkType)
        val telButton = findViewById<Button>(R.id.btGetTel)
        telButton.setOnClickListener {
            try {
                netOp.setText(telephonyManager.networkOperator)
                country.setText(telephonyManager.networkCountryIso)
                val pType = when(telephonyManager.phoneType) {
                    TelephonyManager.PHONE_TYPE_GSM -> "GSM"
                    TelephonyManager.PHONE_TYPE_CDMA -> "CDMA"
                    else -> "Others"
                }
                phoneType.setText(pType)
                val sState = when(telephonyManager.simState) {
                    TelephonyManager.SIM_STATE_READY -> "Ready"
                    TelephonyManager.SIM_STATE_ABSENT -> "ABSENT"
                    else -> "Unknown"
                }
                simState.setText(sState)
                val nT = when(telephonyManager.networkType) {
                    TelephonyManager.NETWORK_TYPE_LTE -> "4G"
                    TelephonyManager.NETWORK_TYPE_NR -> "5G"
                    else -> "Unknown"
                }
                netType.setText(nT)
            } catch(e: Exception) {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            }

        }

    }
//    private fun checkIfEmpty(l:List<EditText>): Boolean {
//        return l.any { it.text.isEmpty() }
//    }
}