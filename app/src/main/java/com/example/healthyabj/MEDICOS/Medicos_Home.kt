package com.example.healthyabj.MEDICOS

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthyabj.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.medico_homepage.*

class Medicos_Home  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.medico_homepage)

        medicos_homeac_logout.setOnClickListener {

            FirebaseAuth.getInstance().signOut();
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }



        medicos_home_localizacao.setOnClickListener {
        startActivity(Intent(this, MapsActivity::class.java))
        }

        medicos_homebtchat.setOnClickListener {

        startActivity(Intent(this, ChatListViewActivity::class.java))

        }
        medicos_homebtConsultas.setOnClickListener{
            val profileName=intent.getStringExtra("TipoUser")
            val intent = Intent(this, ProxConsultasActivitie::class.java)
            intent.putExtra("TipoUser",profileName)
            startActivity(intent)

        }






        medicos_homeac_perfil.setOnClickListener {

            startActivity(Intent(this, PerfilUser::class.java))




        }

}
}