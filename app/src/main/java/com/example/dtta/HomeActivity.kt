package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.net.toUri

class HomeActivity : AppCompatActivity() {

    private lateinit var icProfile: View
    private lateinit var btnChallenge: View
    private lateinit var btnStrava: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        icProfile = findViewById(R.id.icProfile)
        btnChallenge = findViewById(R.id.btnChallenge)
        btnStrava = findViewById(R.id.btnStrava)

        icProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnChallenge.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    "https://www.strava.com/challenges".toUri()
                )
            )
        }

        btnStrava.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    "https://www.strava.com/".toUri()
                )
            )
        }
    }
}
