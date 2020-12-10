package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthyabj.MEDICOS.Medicos_Home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.minhasconsultasuser.*

class ProxConsultasActivitie : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.minhasconsultasuser)
        auth = FirebaseAuth.getInstance()


        minhasconsultasuserbtHome.setOnClickListener {
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
                        }
                    }
                }
        }


        val db = FirebaseFirestore.getInstance()


        db.collection("ConsultasExis").orderBy("DiaConsulta", Query.Direction.ASCENDING)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val result: StringBuffer = StringBuffer()

                    for (document in task.result) {
                        var DC : String
                        var HC : String
                        var NM : String
                        var NP : String

                            DC = result.append(document.data.getValue("DiaConsulta")).toString()
                            HC = result.append(document.data.getValue("NomeMedico")).toString()
                            NM = result.append(document.data.getValue("NomeMedico")).toString()
                            NP = result.append(document.data.getValue("NomePaciente")).toString()


                            var todoList = mutableListOf(   Consultas(DC,HC,NM,NP)  )




                        val adapter = MinhasConsultasAdapter(todoList)
                        rvTODO.adapter = adapter
                        rvTODO.layoutManager = LinearLayoutManager(this)




                    }
                } else {
                    Log.w(ChatLogActivity.TAG, "Error getting documents.", task.exception)
                }
            }





    }}