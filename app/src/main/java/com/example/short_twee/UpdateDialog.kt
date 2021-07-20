package com.example.short_twee

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment

class UpdateDialog : DialogFragment() {

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

    override fun onStart() {
        super.onStart()

        dialog?.findViewById<CardView>(R.id.cv_update_cancel)?.setOnClickListener { _ ->
            dialog?.cancel()
        }

        dialog?.findViewById<CardView>(R.id.cv_update_process)?.setOnClickListener { _ ->
            dialog?.cancel()
        }
    }
}