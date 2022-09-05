package com.example.demoadmin.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoadmin.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth:FirebaseAuth
    lateinit var binding: ActivityLoginBinding
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        binding.btLogin.setOnClickListener {
            startActivity(Intent(context,MainActivity::class.java))
        }



      /*  firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.createUserWithEmailAndPassword("","").addOnCompleteListener {
            if (it.isSuccessful){

            }else{}

        }*/

    }
}