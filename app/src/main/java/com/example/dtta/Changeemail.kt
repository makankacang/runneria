package com.example.dtta

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Changeemail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changeemail)

        val sharedPreferences =
            getSharedPreferences("UserData", MODE_PRIVATE)

        val currentEmail =
            sharedPreferences.getString(
                "email",
                "haikalalghiffari05@gmail.com"
            )

        val etCurrentEmail =
            findViewById<EditText>(R.id.etCurrentEmail)

        etCurrentEmail.setText(currentEmail)

        val etNewEmail =
            findViewById<EditText>(R.id.etNewEmail)

        val btnNext =
            findViewById<Button>(R.id.btnNext)

        val btnBack =
            findViewById<TextView>(R.id.btnBack)

        btnBack.setOnClickListener {

            val intent = Intent(this, Setting::class.java)
            startActivity(intent)
            finish()
        }

        btnNext.setOnClickListener {

            val email =
                etNewEmail.text.toString().trim()

            if (email.isEmpty()) {

                Toast.makeText(
                    this,
                    "Email tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (!email.contains("@")) {

                Toast.makeText(
                    this,
                    "Format email tidak valid",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val editor =
                    sharedPreferences.edit()

                editor.putString("email", email)
                editor.apply()

                Toast.makeText(
                    this,
                    "Email anda berhasil di ubah",
                    Toast.LENGTH_SHORT
                ).show()

                val intent =
                    Intent(this, Setting::class.java)

                startActivity(intent)
                finish()
            }
        }
    }
}