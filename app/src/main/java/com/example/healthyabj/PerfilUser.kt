package com.example.healthyabj

import android.content.Intent
import android.nfc.tech.NfcF.get
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthyabj.MEDICOS.Medicos_Home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.opcoes.*
import kotlinx.android.synthetic.main.perfiluser.*
import kotlinx.android.synthetic.main.signin.*

class PerfilUser : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfiluser)

        perfiluserbtHome.setOnClickListener {
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

        val imageperfil: ImageView = findViewById(R.id.fotoperfil)


        db.collection("User")
            .addSnapshotListener { value, e ->
                val result: StringBuffer = StringBuffer()

                for (doc in value!!) {
                    if (doc.get("uid") == FirebaseAuth.getInstance().currentUser?.uid.toString()){

                        perfiluserNome.text = doc.get("name").toString()
                        perfiluserEmail.text = doc.get("email").toString()
                        Picasso.with(this@PerfilUser).load(doc.get("profileImageUrl").toString())
                            .into(imageperfil)
                    }



                }
            }


       /* fun ShowData() {


            val database = Firebase
            val uid = FirebaseAuth.getInstance().uid
            val ref = FirebaseDatabase.getInstance().getReference("/Users/$uid")


        }*/

      /*  val a = arrayOf<String>("1", "2", "3") //explicit type declaration


        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val uid = FirebaseAuth.getInstance().uid
                var post = dataSnapshot.child("/Users/$uid/name").getValue()

                a[0] = post.toString()
                //  a.set(0,post.toString())



            }

        }
    */



    }


}

