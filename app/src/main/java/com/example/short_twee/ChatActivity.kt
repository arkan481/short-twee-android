package com.example.short_twee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.short_twee.adapters.ChatAdapters
import com.example.short_twee.models.Story
import com.example.short_twee.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : AppCompatActivity() {

    private lateinit var tvAvatar: TextView
    private lateinit var tvEmail: TextView
    private lateinit var ibMenu: ImageButton

    private lateinit var chatAdapters: ChatAdapters
    private lateinit var rvChat: RecyclerView

    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var cvProcess: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        tvAvatar = findViewById(R.id.tv_head_avatar)
        tvEmail = findViewById(R.id.tv_head_email)
        ibMenu = findViewById(R.id.ib_head_menu)
        rvChat = findViewById(R.id.rv_chat)
        etTitle = findViewById(R.id.et_insert_title)
        etContent = findViewById(R.id.et_insert_content)
        cvProcess = findViewById(R.id.cv_insert_process)

        setupHeader()

        bindEvents()

        setupChatAdapter()
    }

    private fun setupChatAdapter() {
        val stories = ArrayList<Story>()
        // TODO: Fetch From Firebase
        stories.add(
            Story(
                id = "debug",
                title = "Ini Title",
                content = "Ini Content",
                createdAt = "Sun Jul 04 2021 19:11:08 GMT+0700 (Western Indonesia Time)",
                user = User(
                    id = FirebaseAuth.getInstance().currentUser!!.uid,
                    name = FirebaseAuth.getInstance().currentUser!!.email!!
                )
            )
        )
        stories.add(
            Story(
                id = "debug2",
                title = "Ini Title",
                content = "Ini Content",
                createdAt = "Sun Jul 04 2021 19:11:08 GMT+0700 (Western Indonesia Time)",
                user = User(
                    id = "FirebaseAuth.getInstance().currentUser!!.uid",
                    name = "iniendekocak"
                )
            )
        )
        chatAdapters = ChatAdapters(stories)
        rvChat.adapter = chatAdapters
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

        cvProcess.setOnClickListener {
            val story = Story(
                id = "implementAutoId",
                title = etTitle.text.toString(),
                content = etContent.text.toString(),
                createdAt = Date().toString(),
                user = User(id = FirebaseAuth.getInstance().currentUser!!.uid, name = FirebaseAuth.getInstance().currentUser!!.email!!)
            )
            // TODO: Insert to firebase
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