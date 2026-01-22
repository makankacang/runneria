package com.example.dtta

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val etNama = findViewById<EditText>(R.id.etName)
        val etLokasi = findViewById<EditText>(R.id.etLocation)
        val etBio = findViewById<EditText>(R.id.etBio)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Terima data awal (kalau ada)
        etNama.setText(intent.getStringExtra("EXTRA_NAMA"))
        etLokasi.setText(intent.getStringExtra("EXTRA_LOKASI"))
        etBio.setText(intent.getStringExtra("EXTRA_BIO"))

        btnSave.setOnClickListener {
            val nama = etNama.text.toString()
            val lokasi = etLokasi.text.toString()
            val bio = etBio.text.toString()

            val intent = Intent()
            intent.putExtra("EXTRA_NAMA", nama)
            intent.putExtra("EXTRA_LOKASI", lokasi)
            intent.putExtra("EXTRA_BIO", bio)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
