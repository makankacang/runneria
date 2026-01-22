package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

    }

    fun goToPrivacyControls(view: View) {
        val intent = Intent(this, PrivacyControls::class.java)
        startActivity(intent)
        // opsional
    }

    fun goToActivities(view: View) {
        val intent = Intent(this, Activities::class.java)
        startActivity(intent)
        // opsional
    }
}