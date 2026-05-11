package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Setup RecyclerView Recent Activities
        setupRecentActivities()

        // Navigasi - existing code
        findViewById<View>(R.id.icProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<View>(R.id.btnChallenge).setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    "https://www.strava.com/challenges".toUri()
                )
            )
        }

        findViewById<CardView>(R.id.btnStrava).setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    "https://www.strava.com/".toUri()
                )
            )
        }

        findViewById<View>(R.id.btnHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        findViewById<View>(R.id.btnMaps).setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        findViewById<View>(R.id.btnProgress).setOnClickListener {
            startActivity(Intent(this, ProgressActivity::class.java))
        }

        findViewById<View>(R.id.btnProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun setupRecentActivities() {
        // Data dummy aktivitas lari terbaru
        val activities = listOf(
            RunActivity(
                title = "Morning Run",
                date = "Mon, 10 May 2026",
                distance = "5.2 km",
                duration = "32:15",
                pace = "6:12"
            ),
            RunActivity(
                title = "Evening Jog",
                date = "Sun, 9 May 2026",
                distance = "3.8 km",
                duration = "24:40",
                pace = "6:30"
            ),
            RunActivity(
                title = "Weekend Long Run",
                date = "Sat, 8 May 2026",
                distance = "10.5 km",
                duration = "1:05:20",
                pace = "6:13"
            ),
            RunActivity(
                title = "Tempo Run",
                date = "Thu, 7 May 2026",
                distance = "6.0 km",
                duration = "33:00",
                pace = "5:30"
            ),
            RunActivity(
                title = "Recovery Run",
                date = "Wed, 6 May 2026",
                distance = "4.0 km",
                duration = "28:10",
                pace = "7:02"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rvRecentActivities)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RunActivityAdapter(activities)

        // Nonaktifkan nested scroll agar scroll dihandle NestedScrollView
        recyclerView.isNestedScrollingEnabled = false
    }
}