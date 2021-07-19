package com.example.short_twee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var rlAuthMove: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        rlAuthMove = findViewById(R.id.rl_auth_move)

        bindEvents()
    }

    /**
     * Methods to bind events in Login Activity
     */
    private fun bindEvents() {
        // bind rlAuthMove click to open the signup activity
        rlAuthMove.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }
}