package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauthentication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var bsu:ActivitySignUpBinding
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bsu = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(bsu.root)

        firebaseAuth = FirebaseAuth.getInstance()

        bsu.Ssignin.setOnClickListener {
            val i = Intent(this,SignIn::class.java)
            startActivity(i)
            finish()
        }
        bsu.ssIgnup.setOnClickListener{
            val eml = bsu.email.text.toString()
            val usr = bsu.username.text.toString()
            val pass = bsu.pass.text.toString()
            val repass = bsu.repass.text.toString()
            val user = users(eml,usr,pass)

            if (eml.isNotEmpty() && usr.isNotEmpty() && pass.isNotEmpty() && repass.isNotEmpty()){
                if( pass == repass){
                    firebaseAuth.createUserWithEmailAndPassword(eml ,pass ).addOnCompleteListener{
                        if(it.isSuccessful){
                            database= FirebaseDatabase.getInstance().getReference("User")
                            database.child(usr).setValue(user).addOnSuccessListener {
                                Toast.makeText(this,"Successfully Registered",Toast.LENGTH_SHORT).show()
                                val i = Intent(this,DialogBox::class.java)
                                startActivity(i)
                                finish()
                            }.addOnFailureListener {
                                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                            }

                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Password Not matching",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty fields Are not allowed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}