package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
            startActivity(Intent(this,HomePageActivity::class.java))
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