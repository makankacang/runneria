package com.example.dtta

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import android.util.Log


class MapsActivity : AppCompatActivity() {

    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            Log.d("MAPS", "MapsActivity onCreate DIPANGGIL")
            setContentView(R.layout.activity_maps)

        val ctx = applicationContext
        Configuration.getInstance().userAgentValue = "dtta-app/1.0"
        Configuration.getInstance()
            .load(ctx, ctx.getSharedPreferences("osmdroid", MODE_PRIVATE))

        val spinnerOverlay = findViewById<LinearLayout>(R.id.spinnerOverlay)
        Handler(mainLooper).postDelayed({
            spinnerOverlay.visibility = View.GONE
        }, 1000)

        map = findViewById(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)

        val kanayakan = GeoPoint(-6.877283, 107.62045)
        map.controller.setZoom(17.0)
        map.controller.setCenter(kanayakan)

        val marker = Marker(map)
        marker.position = kanayakan
        marker.title = "Kanayakan, Polman Bandung"
        map.overlays.add(marker)
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
