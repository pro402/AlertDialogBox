package com.example.firebaseauthentication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.firebaseauthentication.databinding.ActivityDialogBoxBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DialogBox : AppCompatActivity() {
    lateinit var binding: ActivityDialogBoxBinding
    lateinit var dialog: Dialog
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = Dialog(this)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val dialogView = layoutInflater.inflate(R.layout.dialll,null)
        dialog.setContentView(dialogView)

        val name = dialog.findViewById<TextInputEditText>(R.id.name)
        val phone = dialog.findViewById<TextInputEditText>(R.id.phonenumber)

        dialogView.findViewById<Button>(R.id.add)?.setOnClickListener {
            val n = name.text.toString()
            val p = phone.text.toString()
            val contacts = contacts(n,p)
            database= FirebaseDatabase.getInstance().getReference("User/Contacts")
            database.child(p).setValue(contacts).addOnSuccessListener {
                Toast.makeText(this,"Successfully Registered contact ${n} and phone number ${p}", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

            name.setText("")
            phone.setText("")

        }

        binding.dialogButton.setOnClickListener {
            dialog.show()
        }

    }
}

