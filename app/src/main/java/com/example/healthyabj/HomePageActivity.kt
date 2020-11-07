package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.homepage.*

class HomePageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        auth = FirebaseAuth.getInstance()


        homepagebtPerfil.setOnClickListener {
            startActivity(Intent(this,PerfilUser::class.java))

        }


        homepagebtSignOut.setOnClickListener {
           //sai da conta

        }

        homepagebtChat.setOnClickListener {
            //vai para o chat
            startActivity(Intent(this,PerfilUser::class.java))
        }


        homepagebtProximasConsultas.setOnClickListener {
            startActivity(Intent(this,ProxConsultasActivitie::class.java))
        }

        homepagebtLocalizacao.setOnClickListener {
            startActivity(Intent(this,ProxConsultasActivitie::class.java))


        }












    }
}