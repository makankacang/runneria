package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class LoginActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    private val googleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleGoogleSignInResult(result.data)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupGoogleSignIn()
    }

    private fun setupGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun handleGoogleSignInResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)

        try {
            task.getResult(ApiException::class.java)

            Log.d("LOGIN", "LOGIN GOOGLE BERHASIL")

            startActivity(Intent(this, MapsActivity::class.java))
            finish()

        } catch (e: ApiException) {
            Log.e("LOGIN", "LOGIN GOOGLE GAGAL", e)
        }
    }

    fun loginGoogle(View: View) {
        googleLauncher.launch(googleSignInClient.signInIntent)
    }

    fun btnLgn(View: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
