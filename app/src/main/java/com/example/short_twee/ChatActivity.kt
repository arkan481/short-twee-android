package com.example.short_twee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChatActivity : AppCompatActivity() {

    private lateinit var tvAvatar: TextView
    private lateinit var tvEmail: TextView
    private lateinit var ibMenu: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        tvAvatar = findViewById(R.id.tv_head_avatar)
        tvEmail = findViewById(R.id.tv_head_email)
        ibMenu = findViewById(R.id.ib_head_menu)

        setupHeader()

        bindEvents()
    }

    private fun bindEvents() {
        ibMenu.setOnClickListener { v ->
            PopupMenu(this, v).let { popupMenu ->
                popupMenu.menuInflater.inflate(R.menu.actions, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { item ->
                    return@setOnMenuItemClickListener when (item.itemId) {
                        R.id.menu_logout -> {
                            logout()
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.show()
            }
        }
    }

    private fun logout() {
        Firebase.auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun setupHeader() {
        val userEmail = FirebaseAuth.getInstance().currentUser!!.email!!;

        tvEmail.text = userEmail
        tvAvatar.text = userEmail.uppercase()[0].toString()

    }
}