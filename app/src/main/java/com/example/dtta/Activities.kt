package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activities : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        findViewById<View>(R.id.btnHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        findViewById<View>(R.id.btnMaps).setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        findViewById<View>(R.id.btnActivities).setOnClickListener {
            startActivity(Intent(this, TrainingActivity::class.java))
        }

        findViewById<View>(R.id.btnProgress).setOnClickListener {
            startActivity(Intent(this, ProgressActivity::class.java))
        }

        findViewById<View>(R.id.btnProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

    }

    fun goToSettings(view: View) {
        val intent = Intent(this, Setting::class.java)
        startActivity(intent)
    }
}