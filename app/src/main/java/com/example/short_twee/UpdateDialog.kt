package com.example.short_twee

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import com.example.short_twee.models.Story

class UpdateDialog : DialogFragment() {

    private lateinit var bundle: Bundle

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.update_dialog, null))
            val dialog = builder.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return dialog
        }?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bundle = requireArguments()
    }

    override fun onStart() {
        super.onStart()

        val updStory = bundle.getParcelable<Story>("story")

        dialog?.findViewById<EditText>(R.id.et_update_title)!!.setText(updStory!!.title)
        dialog?.findViewById<EditText>(R.id.et_update_content)!!.setText(updStory!!.content)

        dialog?.findViewById<CardView>(R.id.cv_update_cancel)?.setOnClickListener { _ ->
            dialog?.cancel()
        }

        dialog?.findViewById<CardView>(R.id.cv_update_process)?.setOnClickListener { _ ->
            //TODO: Update to firebase
            dialog?.cancel()
        }
    }
}