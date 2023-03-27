package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauthentication.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {
    private lateinit var bsi: ActivitySignInBinding
    private lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bsi= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(bsi.root)

        firebaseAuth = FirebaseAuth.getInstance()

        bsi.signup.setOnClickListener{
            val i = Intent(this,SignUp::class.java)
            startActivity(i)
            finish()
        }
        bsi.SignIn.setOnClickListener{
            val eml = bsi.email.text.toString()
            val pass = bsi.pass.text.toString()

            if (eml.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(eml ,pass ).addOnCompleteListener{
                    if(it.isSuccessful){
                        val i = Intent(this,DialogBox::class.java)
                        startActivity(i)
                        finish()
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