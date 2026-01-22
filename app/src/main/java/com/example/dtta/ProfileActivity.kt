package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    private val editProfileLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data

                val nama = data?.getStringExtra("EXTRA_NAMA")
                val lokasi = data?.getStringExtra("EXTRA_LOKASI")
                val bio = data?.getStringExtra("EXTRA_BIO")

                findViewById<TextView>(R.id.tvNama).text = nama
                findViewById<TextView>(R.id.tvLokasi).text = lokasi
                findViewById<TextView>(R.id.tvBio).text = bio
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val closeProfile = findViewById<View>(R.id.closeProfile)
        val btnEdit = findViewById<View>(R.id.btnEdit)

        closeProfile.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btnEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)

            intent.putExtra(
                "EXTRA_NAMA",
                findViewById<TextView>(R.id.tvNama).text.toString()
            )
            intent.putExtra(
                "EXTRA_LOKASI",
                findViewById<TextView>(R.id.tvLokasi).text.toString()
            )
            intent.putExtra(
                "EXTRA_BIO",
                findViewById<TextView>(R.id.tvBio).text.toString()
            )

            editProfileLauncher.launch(intent)
        }
    }
}
