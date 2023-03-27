package com.example.firebaseauthentication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firebaseauthentication.databinding.ActivityDialogBoxBinding

class DialogBox : AppCompatActivity() {
    lateinit var binding: ActivityDialogBoxBinding
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = Dialog(this)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val dialogView = layoutInflater.inflate(R.layout.dialll,null)
        dialog.setContentView(dialogView)

        dialogView.findViewById<Button>(R.id.add)?.setOnClickListener {
            dialog.dismiss()
        }

        binding.dialogButton.setOnClickListener {
            dialog.show()
        }

    }
}

