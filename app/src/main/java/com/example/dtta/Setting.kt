package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Setting : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val sharedPreferences =
            getSharedPreferences("UserData", MODE_PRIVATE)

        val savedEmail =
            sharedPreferences.getString(
                "email",
                "polman123@gmail.com"
            )

        val tvEmailAccount =
            findViewById<TextView>(R.id.akun)

        tvEmailAccount?.text = savedEmail
    }

    fun goToPrivacyControls(view: View) {
        val intent = Intent(this, PrivacyControls::class.java)
        startActivity(intent)
    }

    fun goToActivities(view: View) {
        val intent = Intent(this, Activities::class.java)
        startActivity(intent)
    }

    fun goToChangeemail(view: View) {
        val intent = Intent(this, Changeemail::class.java)
        startActivity(intent)
    }
}