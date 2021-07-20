package com.example.short_twee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var rlAuthMove: RelativeLayout
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var cvProcess: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        rlAuthMove = findViewById(R.id.rl_auth_move)
        etEmail = findViewById(R.id.et_auth_email)
        etPassword = findViewById(R.id.et_auth_password)
        cvProcess = findViewById(R.id.btn_auth_process)

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
        cvProcess.setOnClickListener {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, ChatActivity::class.java))
                    } else {
                        Toast.makeText(this@SignupActivity, "Wrong Credentials!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}