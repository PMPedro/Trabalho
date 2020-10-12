package com.example.healthyabj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class medicosactivity : AppCompatActivity() {

//COMENTARIO


    private lateinit var auth: FirebaseAuth


    //klihbhgu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.medico)
        auth = FirebaseAuth.getInstance()

    }
}