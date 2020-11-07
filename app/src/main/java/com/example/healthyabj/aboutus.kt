package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.aboutus.*

class aboutus : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutus)

        aboutusbtHome.setOnClickListener {
            startActivity(Intent(this,HomePageActivity::class.java))
        }





    }
}