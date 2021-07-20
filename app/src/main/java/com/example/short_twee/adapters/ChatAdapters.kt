package com.example.short_twee.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.short_twee.ChatActivity
import com.example.short_twee.R
import com.example.short_twee.UpdateDialog
import com.example.short_twee.models.Story
import com.google.firebase.auth.FirebaseAuth

class ChatAdapters(private val stories: ArrayList<Story>? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            LayoutInflater.from(parent.context).inflate(R.layout.chat_send_item, parent, false)
                .let { view ->
                    return ViewHolderSend(view)
                }
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.chat_receive_item, parent, false)
                .let { view ->
                    return ViewHolderReceive(view)
                }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            0 -> {
                val viewHolderSend = holder as ViewHolderSend
                viewHolderSend.tvTitle.text = stories!![position].title
                viewHolderSend.tvContent.text = stories!![position].content
                viewHolderSend.tvDate.text = formatDate(stories!![position].createdAt)
                viewHolderSend.tvAvatar.text = stories!![position].user.name.uppercase()[0].toString()
            }
            1 -> {
                val viewHolderSend = holder as ViewHolderReceive
                viewHolderSend.tvTitle.text = stories!![position].title
                viewHolderSend.tvContent.text = stories!![position].content
                viewHolderSend.tvDate.text = formatDate(stories!![position].createdAt)
                viewHolderSend.tvAvatar.text = stories!![position].user.name.uppercase()[0].toString()
            }
        }
    }

    private fun formatDate(dateStr: String) : String {
        return "${dateStr.split(' ')[1]} ${dateStr.split(' ')[2]} ${Regex("(?:[01]\\d|2[0-3]):(?:[0-5]\\d):(?:[0-5]\\d)").find(dateStr)?.value}"
    }

    override fun getItemCount(): Int {
        return stories!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (stories?.get(position)?.user?.id === FirebaseAuth.getInstance().currentUser!!.uid) {
            0
        } else {
            1
        }
    }

    inner class ViewHolderSend(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvContent: TextView
        var tvDate: TextView
        var tvAvatar: TextView
        var ibDelete: ImageButton

        init {
            tvTitle = itemView.findViewById(R.id.tv_chat_title)
            tvContent = itemView.findViewById(R.id.tv_chat_content)
            tvDate = itemView.findViewById(R.id.tv_chat_date)
            tvAvatar = itemView.findViewById(R.id.tv_chat_avatar)
            ibDelete = itemView.findViewById(R.id.ib_chat_delete)

            itemView.setOnLongClickListener {
                val updateDialog = UpdateDialog()

                val bundle = Bundle()
                bundle.putParcelable("story", stories!![adapterPosition])

                updateDialog.arguments = bundle

                updateDialog.show(
                    (itemView.context as ChatActivity).supportFragmentManager,
                    "Update Story"
                )
                true
            }

            ibDelete.setOnClickListener {
                val story = stories!![adapterPosition]
                // TODO: Delete from firebase
            }
        }
    }

    inner class ViewHolderReceive(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvContent: TextView
        var tvAvatar: TextView
        var tvDate: TextView
        init {
            tvTitle = itemView.findViewById(R.id.tv_chat_title)
            tvContent = itemView.findViewById(R.id.tv_chat_content)
            tvDate = itemView.findViewById(R.id.tv_chat_date)
            tvAvatar = itemView.findViewById(R.id.tv_chat_avatar)
        }
    }
}