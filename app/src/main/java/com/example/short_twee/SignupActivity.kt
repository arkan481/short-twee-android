package com.example.short_twee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout

class SignupActivity : AppCompatActivity() {

    private lateinit var rlAuthMove: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        rlAuthMove = findViewById(R.id.rl_auth_move)

        bindEvents()
    }

    /**
     * Methods to bind events in Signup Activity
     */
    private fun bindEvents() {
        // bind rlAuthMove click to open the signup activity
        rlAuthMove.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}