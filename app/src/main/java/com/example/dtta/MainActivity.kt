package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MainActivity : AppCompatActivity() {

    private lateinit var map: MapView
    private lateinit var btnRecord: Button
    private lateinit var timerText: TextView
    private var timerRunning = false
    private var countDownTimer: CountDownTimer? = null
    private val timerDuration = 5 * 60 * 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().userAgentValue = packageName
        setContentView(R.layout.activity_main)

        map = findViewById(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        val kanayakan = GeoPoint(-6.877283, 107.62045)
        map.controller.setZoom(17.0)
        map.controller.setCenter(kanayakan)

        val marker = Marker(map)
        marker.position = kanayakan
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = "Kanayakan, Polman Bandung"
        map.overlays.add(marker)


        btnRecord = findViewById(R.id.btnRecord)
        timerText = findViewById(R.id.tvTimer)

        btnRecord.setOnClickListener {
            if (!timerRunning) {
                startTimer()
                btnRecord.text = "STOP"
            } else {
                stopTimer()
                btnRecord.text = "START"
            }
        }

    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timerText.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                timerText.text = "00:00"
                timerRunning = false
                btnRecord.text = "START"
            }
        }.start()
        timerRunning = true
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        timerText.text = "00:00"
        timerRunning = false
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
        countDownTimer?.cancel()
    }
}
