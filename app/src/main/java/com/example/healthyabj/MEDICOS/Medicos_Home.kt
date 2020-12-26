package com.example.healthyabj.MEDICOS

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthyabj.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.medico_homepage.*

class Medicos_Home  : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.medico_homepage)

        lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
        auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        medicos_homeac_logout.setOnClickListener {

            FirebaseAuth.getInstance().signOut();
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }



        val user = FirebaseAuth.getInstance().currentUser
        val useremail = user?.email

        fun ReceiveDataPlace () {
        val docRef = db.collection("User").document(useremail.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {

                  var email = document.get("email").toString()
                  //var data = document.get("EmaiPaciente").toString()
                  var nome = document.get("name").toString()


                    tvmedicoshomepageNome.setText(nome)
                    tvmedicoshomepageEmail.setText(email)

                } else {


                }
            }



            .addOnFailureListener { exception ->

            }

        }

        ReceiveDataPlace()





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






//        medicos_homeac_perfil.setOnClickListener {
//
//            startActivity(Intent(this, PerfilUser::class.java))
//
//
//
//
//        }

}
}