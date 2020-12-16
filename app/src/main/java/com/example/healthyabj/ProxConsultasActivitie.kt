package com.example.healthyabj

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthyabj.MEDICOS.Medicos_Home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.minhasconsultasuser.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ProxConsultasActivitie : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    @RequiresApi(Build.VERSION_CODES.O)
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
                                startActivity(Intent(this, HomePageActivity::class.java))
                            }

                            if(cenas == "1") {

                                startActivity(Intent(this, Medicos_Home::class.java))
                            }
                        }
                    }
                }
        }





        val db = FirebaseFirestore.getInstance()
//
//
//        db.collection("Consultas").orderBy("DiaConsulta", Query.Direction.ASCENDING)
//            .get()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//
//                    val result: StringBuffer = StringBuffer()
//
//                    for (document in task.result) {
//                        var DC : String
//                        var HC : String
//                        var NM : String
//                        var NP : String
//
//                            DC = result.append(document.data.getValue("DiaConsulta")).toString()
//                            NP = result.append(document.data.getValue("EmaiPaciente")).toString()
//                            NM = result.append(document.data.getValue("EmailMedico")).toString()
//                            HC = result.append(document.data.getValue("HoraConsulta")).toString()
//
//
//
//                        var todoList = mutableListOf(Consultas(DC, NP, NM, HC))
//
//
//
//
//                        val adapter = MinhasConsultasAdapter(todoList)
//                        rvTODO.adapter = adapter
//                        rvTODO.layoutManager = LinearLayoutManager(this)
//
//
//
//
//                    }
//                } else {
//                    Log.w(ChatLogActivity.TAG, "Error getting documents.", task.exception)
//                }
//            }





fun ReceDia() {
        val fromId= FirebaseAuth.getInstance().uid

        db.collection("Consultas")
            .orderBy("DiaConsulta", Query.Direction.ASCENDING)
            .addSnapshotListener { value, e ->

                if (e != null) {
                    Log.w(ChatLogActivity.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }



                for (doc in value!!) {

                    var DC : String
                    var HC : String
                    var NM : String
                    var NP : String


                    DC = doc.get("DiaConsulta").toString()
                    NP = doc.get("EmaiPaciente").toString()
                    NM = doc.get("EmailMedico").toString()
                    HC = doc.get("HoraConsulta").toString()

if(DC.toString().equals( tvconsultasdatepickertext.text)){

                    var todoList = mutableListOf(Consultas(DC, NP, NM, HC))




                    val adapter = MinhasConsultasAdapter(todoList)
                    rvTODO.adapter = adapter
                    rvTODO.layoutManager = LinearLayoutManager(this)




}
                    else{
    Toast.makeText(this, "Nao existem consultas nesse Dia", Toast.LENGTH_SHORT).show()



    Toast.makeText(this, "Nao existem consultas nesse Dia", Toast.LENGTH_SHORT).show()
    Toast.makeText(this, "Nao existem consultas nesse Dia", Toast.LENGTH_SHORT).show()

                }
            }

    }
}




        //fazer aqui cena das datas
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        btconsultasdatepicker.setOnClickListener {
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, mYear,mMonth,mDay ->
                tvconsultasdatepickertext.setText(""+mYear+"/"+(mMonth+1)+"/"+mDay)


            },year,month,day)
            dpd.show()



        }



        ReceDia()

    }}


