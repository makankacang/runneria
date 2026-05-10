package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var icProfile: View
    private lateinit var btnSearch: View
    private lateinit var btnChallenge: View
    private lateinit var btnStrava: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        icProfile = findViewById(R.id.icProfile)
        btnSearch = findViewById(R.id.btnSearch)
        btnChallenge = findViewById(R.id.btnChallenge)
        btnStrava = findViewById(R.id.btnStrava)

        icProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnSearch.setOnClickListener {
            startActivity(Intent(this, FriendsActivity::class.java))
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

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.navigation_home
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> true
                R.id.navigation_maps -> {
                    startActivity(Intent(this, MapsActivity::class.java))
                    true
                }
                R.id.navigation_activity -> {
                    startActivity(
                        Intent(this, ProgressActivity::class.java)
                            .putExtra(ProgressActivity.EXTRA_INITIAL_TAB, ProgressActivity.TAB_ACTIVITIES)
                    )
                    true
                }
                R.id.navigation_progress -> {
                    startActivity(Intent(this, ProgressActivity::class.java))
                    true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
