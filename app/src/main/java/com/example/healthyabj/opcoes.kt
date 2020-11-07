package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.aboutus.*
import kotlinx.android.synthetic.main.opcoes.*

class opcoes : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opcoes)
        auth = FirebaseAuth.getInstance()

        opcoesbtHome.setOnClickListener {
            startActivity(Intent(this,HomePageActivity::class.java))
        }

        opcoesbtAboutUs.setOnClickListener {
            startActivity(Intent(this,aboutus::class.java))
        }



}
}