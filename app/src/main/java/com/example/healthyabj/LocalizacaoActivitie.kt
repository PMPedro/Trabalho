package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.localizacaohospital.*

class LocalizacaoActivitie : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.localizacaohospital)
        auth = FirebaseAuth.getInstance()


        localizacaohospitalbtHome.setOnClickListener {
            startActivity(Intent(this,HomePageActivity::class.java))
        }


        localizacaohospitalbt.setOnClickListener {

            startActivity(Intent(this,MapsActivity::class.java))
        }






}
}