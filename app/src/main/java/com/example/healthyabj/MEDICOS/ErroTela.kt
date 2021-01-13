package com.example.healthyabj.MEDICOS

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthyabj.MainActivity
import com.example.healthyabj.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.errornpermissao.*

class ErroTela: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.errornpermissao)

        bterropermissaosair.setOnClickListener {


            FirebaseAuth.getInstance().signOut();
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }
    }