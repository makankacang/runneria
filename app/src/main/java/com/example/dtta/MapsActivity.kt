package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import android.os.Handler
import android.widget.Button

class MapsActivity : AppCompatActivity() {

    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ctx = applicationContext
        Configuration.getInstance().userAgentValue = "dtta-app/1.0"
        Configuration.getInstance().load(ctx, ctx.getSharedPreferences("osmdroid", MODE_PRIVATE))

        setContentView(R.layout.activity_maps)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnTraining = findViewById<Button>(R.id.btnTraining)

        btnStart.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnTraining.setOnClickListener {
            startActivity(Intent(this, TrainingActivity::class.java))
        }

        val btnDetail = findViewById<Button>(R.id.btnDetailActivity)
        btnDetail.setOnClickListener {
            startActivity(Intent(this, ActivityDetail::class.java))
        }


        val spinnerOverlay = findViewById<LinearLayout>(R.id.spinnerOverlay)

        Handler(mainLooper).postDelayed({
            spinnerOverlay.visibility = View.GONE
        }, 1000)

        map = findViewById(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)
        map.setUseDataConnection(true)

        val kanayakan = GeoPoint(-6.877283, 107.62045)
        map.controller.setZoom(17.0)
        map.controller.setCenter(kanayakan)

        val marker = Marker(map)
        marker.position = kanayakan
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = "Kanayakan, Polman Bandung"
        map.overlays.add(marker)

        val etSearch = findViewById<android.widget.EditText>(R.id.etSearch)
        val tvActivity = findViewById<android.widget.TextView>(R.id.tvActivity)

        tvActivity.setOnClickListener {
            val intent = android.content.Intent(this, ResultActivity::class.java)
            intent.putExtra("keyword", etSearch.text.toString())
            startActivity(intent)
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


    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }
}
