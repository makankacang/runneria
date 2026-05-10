package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        findViewById<View>(R.id.btnUser).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<View>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }

        findViewById<View>(R.id.tabProgress).setOnClickListener {
            // Current tab.
        }

        findViewById<View>(R.id.tabActivities).setOnClickListener {
            startActivity(Intent(this, Activities::class.java))
        }
    }
}
