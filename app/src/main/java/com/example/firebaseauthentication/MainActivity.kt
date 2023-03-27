package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauthentication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b= ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.MainSignIn.setOnClickListener{
            Toast.makeText(this,"Forwarding TO Sign In Page",Toast.LENGTH_LONG).show()
            val i = Intent(this,SignIn::class.java)
            startActivity(i)
        }
        b.MainSignUp.setOnClickListener{
            Toast.makeText(this,"Forwarding TO Sign Up Page",Toast.LENGTH_LONG).show()
            val i = Intent(this,SignUp::class.java)
            startActivity(i)
        }
    }
}