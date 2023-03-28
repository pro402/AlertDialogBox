package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauthentication.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {
    private lateinit var bsi: ActivitySignInBinding
    private lateinit var firebaseAuth:FirebaseAuth
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bsi= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(bsi.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("User")

        bsi.signup.setOnClickListener{
            val i = Intent(this,SignUp::class.java)
            startActivity(i)
            finish()
        }
        bsi.SignIn.setOnClickListener{
            val eml = bsi.email.text.toString()
            val pass = bsi.pass.text.toString()
            val usid = bsi.uid.text.toString()

            if (eml.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(eml ,pass ).addOnCompleteListener{
                    if(it.isSuccessful){
                        if (usid.isNotBlank()) {
                            database.child(usid).get().addOnSuccessListener { dataSnapshot ->
                                if (dataSnapshot.exists()) {
                                    val passwd = dataSnapshot.child("password").value

                                    if (passwd.toString() == pass) {
                                        intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        Toast.makeText(this, "Wrong Password", Toast.LENGTH_LONG).show()
                                    }
                                } else {
                                    Toast.makeText(this, "Try Sign Up first", Toast.LENGTH_LONG).show()
                                }
                            }.addOnFailureListener {
                                Toast.makeText(this, "FAILED TO CONNECT DB", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(this, "Please Enter User Unique Id", Toast.LENGTH_SHORT).show()
                        }

                    }else{
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this,"Empty fields Are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}