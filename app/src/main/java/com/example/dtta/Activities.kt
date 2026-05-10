package com.example.dtta

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Activities : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, ProgressActivity::class.java)
        intent.putExtra(ProgressActivity.EXTRA_INITIAL_TAB, ProgressActivity.TAB_ACTIVITIES)
        startActivity(intent)
        finish()
    }
}
