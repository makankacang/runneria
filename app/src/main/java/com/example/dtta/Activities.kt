package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity

class Activities : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        findViewById<View>(R.id.btnUser).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<View>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }

        findViewById<View>(R.id.tabProgress).setOnClickListener {
            startActivity(Intent(this, ProgressActivity::class.java))
        }

        findViewById<View>(R.id.tabActivities).setOnClickListener {
            // Current tab.
        }

        val activityCardOne = findViewById<View>(R.id.activityCardOne)
        val activityCardTwo = findViewById<View>(R.id.activityCardTwo)

        findViewById<SearchView>(R.id.searchActivities).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterActivities(query.orEmpty(), activityCardOne, activityCardTwo)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterActivities(newText.orEmpty(), activityCardOne, activityCardTwo)
                return true
            }
        })
    }

    private fun filterActivities(keyword: String, activityCardOne: View, activityCardTwo: View) {
        val text = keyword.trim()
        activityCardOne.visibility = if (text.isBlank() || "Tugas Runneria 1 5.00 km Oktober 20 2025".contains(text, ignoreCase = true)) {
            View.VISIBLE
        } else {
            View.GONE
        }
        activityCardTwo.visibility = if (text.isBlank() || "Tugas Runneria 2 3.00 km November 18 2025".contains(text, ignoreCase = true)) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
