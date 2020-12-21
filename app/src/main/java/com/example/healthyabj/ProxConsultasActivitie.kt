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






fun ReceDia( date : String ) {

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




                    var todoList = mutableListOf(Consultas(DC, NP, NM, HC))
                    val result: StringBuffer = StringBuffer()


                    val profileName=intent.getStringExtra("TipoUser")
                    val um = 1
                    val zero = 0
                    if (profileName== um.toString() ){





                        if(date.equals(DC) == true   ){


                            val user = FirebaseAuth.getInstance().currentUser
                            if (user != null) {
                                val useremail = user.email
                            } else {

                            }
                            val useremail = user?.email

                            if(useremail == NM){


   val adapter = MinhasConsultasAdapter(todoList)
                            rvTODO.adapter = adapter
                            rvTODO.layoutManager = LinearLayoutManager(this)


                            }
                            else{
                                Toast.makeText(this, "Nao existem Consultas neste dia! ", Toast.LENGTH_SHORT).show()

                            }

                        }


                   }






                  /*  else{
    Toast.makeText(this, "Nao existem consultas nesse Dia", Toast.LENGTH_SHORT).show()





                }*/
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
                if ((mDay < 10) && (mMonth < 10 ) )
                {

                    tvconsultasdatepickertext.setText(""+mYear+"-"+"0"+(mMonth+1)+"-"+"0"+mDay)
                    var date = (""+mYear+"-"+"0"+(mMonth+1)+"-"+"0"+mDay)
                    ReceDia(date)
                }


                else if (mMonth < 10 )
                {
                    tvconsultasdatepickertext.setText(""+mYear+"-"+"0"+(mMonth+1)+"-"+mDay)
                    var date = (""+mYear+"-"+"0"+(mMonth+1)+"-"+mDay)
                    ReceDia(date)
                }


                else if (mDay < 10 )
                {
                    tvconsultasdatepickertext.setText(""+mYear+"-"+(mMonth+1)+"-"+"0"+mDay)
                    var date = (""+mYear+"-"+(mMonth+1)+"-"+"0"+mDay)
                    ReceDia(date)
                }



                else
                {
                    tvconsultasdatepickertext.setText(""+mYear+"-"+(mMonth+1)+"-"+mDay)
                    var date = (""+mYear+"-"+(mMonth+1)+"-"+mDay)
                    ReceDia(date)
                }





              //  recebedia2 (date)

            },year,month,day)
            dpd.show()



        }



       //
        //
    }}


