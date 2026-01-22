package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun btnLgn(View: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}