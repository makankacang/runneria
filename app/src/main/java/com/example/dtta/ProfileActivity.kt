package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvNama: TextView
    private lateinit var tvLokasi: TextView
    private lateinit var tvBio: TextView

    private val editProfileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let {
                    tvNama.text = it.getStringExtra("EXTRA_NAMA")
                    tvLokasi.text = it.getStringExtra("EXTRA_LOKASI")
                    tvBio.text = it.getStringExtra("EXTRA_BIO")
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvNama = findViewById(R.id.tvNama)
        tvLokasi = findViewById(R.id.tvLokasi)
        tvBio = findViewById(R.id.tvBio)

        findViewById<View>(R.id.closeProfile).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        findViewById<View>(R.id.btnEdit).setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("EXTRA_NAMA", tvNama.text.toString())
            intent.putExtra("EXTRA_LOKASI", tvLokasi.text.toString())
            intent.putExtra("EXTRA_BIO", tvBio.text.toString())
            editProfileLauncher.launch(intent)
        }

        findViewById<View>(R.id.btnHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        findViewById<View>(R.id.btnMaps).setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
            finish()
        }

        findViewById<View>(R.id.btnProgress).setOnClickListener {
            startActivity(Intent(this, ProgressActivity::class.java))
            finish()
        }
    }
}
