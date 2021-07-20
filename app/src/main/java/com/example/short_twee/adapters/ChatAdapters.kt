package com.example.short_twee.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.short_twee.ChatActivity
import com.example.short_twee.R
import com.example.short_twee.UpdateDialog

class ChatAdapters : RecyclerView.Adapter<ChatAdapters.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.chat_receive_item, parent, false)
            .let { view ->
                return ViewHolder(view)
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnLongClickListener { view ->
                UpdateDialog().show((itemView.context as ChatActivity).supportFragmentManager, "Update Story")
                true
            }
        }
    }
}