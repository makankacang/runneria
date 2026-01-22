package com.example.dtta

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        val keyword = intent.getStringExtra("keyword") ?: ""

        val tvKeyword = findViewById<TextView>(R.id.tvKeyword)
        val btnMaps = findViewById<TextView>(R.id.btnMaps)

        tvKeyword.text = keyword

        val recs = listOf(
            "$keyword Park",
            "$keyword Stadium",
            "$keyword City Walk",
            "$keyword Outdoor Area",
            "$keyword Public Space"
        )

        val cards = listOf(
            Pair(R.id.card1, R.id.tvRec1),
            Pair(R.id.card2, R.id.tvRec2),
            Pair(R.id.card3, R.id.tvRec3)
        )

        cards.forEachIndexed { index, pair ->
            val card = findViewById<CardView>(pair.first)
            val text = findViewById<TextView>(pair.second)
            text.text = recs[index]

            card.setOnClickListener {
                val uri = Uri.parse("geo:0,0?q=${Uri.encode(recs[index])}")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }


        btnMaps.setOnClickListener {
            val uri = Uri.parse("geo:0,0?q=${Uri.encode(keyword)}")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}
