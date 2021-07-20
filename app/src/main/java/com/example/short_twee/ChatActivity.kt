package com.example.short_twee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.short_twee.adapters.ChatAdapters
import com.example.short_twee.models.Story
import com.example.short_twee.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
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

    private lateinit var database: FirebaseDatabase
    private lateinit var storyRef: DatabaseReference

    private val stories = ArrayList<Story>()

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

        setupChatAdapter()

        listenToDb()

        bindEvents()

    }

    private fun setupChatAdapter() {
        chatAdapters = ChatAdapters(stories)
        rvChat.adapter = chatAdapters
    }

    private fun listenToDb() {
        database = Firebase.database("https://short-twee-default-rtdb.asia-southeast1.firebasedatabase.app/")
        storyRef = database.getReference("stories")

        val storiesListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                stories.clear()
                p0.children.forEach { dataSnapshot ->
                    val story = dataSnapshot.getValue<Story>()
                    stories.add(story!!)
                }
                chatAdapters.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@ChatActivity, "error fetching data", Toast.LENGTH_SHORT).show()
                Log.w("TAG", "loadPost:onCancelled", p0.toException())
            }
        }
        storyRef.addValueEventListener(storiesListener)
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
            val autoId = storyRef.push().key!!
            val story = Story(
                id = autoId,
                title = etTitle.text.toString(),
                content = etContent.text.toString(),
                createdAt = Date().toString(),
                user = User(id = FirebaseAuth.getInstance().currentUser!!.uid, name = FirebaseAuth.getInstance().currentUser!!.email!!)
            )
            storyRef.child(autoId).setValue(story)
            etTitle.setText("")
            etContent.setText("")

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