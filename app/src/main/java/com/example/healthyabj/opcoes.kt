package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthyabj.MEDICOS.Medicos_Home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.opcoes.*

class opcoes : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opcoes)
        auth = FirebaseAuth.getInstance()

        opcoesbtHome.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val uid = auth.currentUser!!.email



            db.collection("User")
                .addSnapshotListener { value, e ->
                    val result: StringBuffer = StringBuffer()

                    for (doc in value!!) {
                        if (doc.get("uid") == FirebaseAuth.getInstance().currentUser?.uid.toString()){

                            var cenas = doc.get("usertype").toString()
                            Toast.makeText(this, cenas.toString(), Toast.LENGTH_SHORT).show()
                            //Toast.makeText(this, perfiluserNome, Toast.LENGTH_SHORT).show()

                            if(cenas == "0"){

                                //Toast.makeText(this, tipo, Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this,HomePageActivity::class.java))
                            }

                            if(cenas == "1") {

                                startActivity(Intent(this, Medicos_Home::class.java))
                            }

                            if(cenas == "2")
                            {
                                startActivity(Intent(this, Medicos_Home::class.java))
                            }
                        }
                    }
                }
        }




}
}