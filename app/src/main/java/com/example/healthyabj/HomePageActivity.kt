package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
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


        homepagebtChat.setOnClickListener{

            startActivity(Intent(this,ChatActivity::class.java))


        }
            homepagebtPerfil.setOnClickListener{
                startActivity(Intent(this,PerfilUser::class.java))
            }

        homepagebtSignOut.setOnClickListener {

            FirebaseAuth.getInstance().signOut();
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        homepagebtPerfil.setOnClickListener{
            startActivity(Intent(this,PerfilUser::class.java))
        }


    }
}