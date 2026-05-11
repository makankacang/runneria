package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrainingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rvTraining)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val trainingList = listOf(
            Training("Running Beginner", "00 : 15 : 00"),
            Training("Stretching Session", "00 : 30 : 00"),
            Training("Full Cardio", "01 : 00 : 00")
        )

        recyclerView.adapter = TrainingAdapter(trainingList)
    }
}