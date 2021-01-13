package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.healthyabj.ChatLogActivity.Companion.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.homepage.*
import kotlinx.android.synthetic.main.medico_homepage.*

class HomePageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()



        val user = FirebaseAuth.getInstance().currentUser
        val useremail = user?.email
       // val usImage: ImageView = findViewById(R.id.imageUser)






        fun ReceiveDataPlace () {


            val fromId = FirebaseAuth.getInstance().uid
            val imageperfil: ImageView = findViewById(R.id.imageUser)
            db.collection("User")

                .addSnapshotListener { value, e ->

                    if (e != null) {
                        Log.w(ChatLogActivity.TAG, "Listen failed.", e)
                        return@addSnapshotListener
                    }



                    for (doc in value!!) {

                        var email = doc.get("email").toString()
                        var name = doc.get("name").toString()
                        var dataNascimento = doc.get("dateNascimento").toString()

                        if (email.toLowerCase() == useremail) {

                            tvhomepageEmail.setText(useremail)
                            tvhomepageNome.setText(name)
                          //  tvhomepageDatanas.setText(dataNascimento)
                            Picasso.with(this@HomePageActivity).load(doc.get("profileImageUrl").toString())
                                .into(imageperfil)

                        }
                    }
                }
        }

        ReceiveDataPlace()


//        homepagebtSignOut.setOnClickListener {
//
//            FirebaseAuth.getInstance().signOut();
//            startActivity(Intent(this,MainActivity::class.java))
//            finish()
//        }

        homepagebtChat.setOnClickListener {
            //vai para o chat
            val intent = Intent(this,NewMessageActivity::class.java)
            startActivity(intent)
        }


        homepagebtProximasConsultas.setOnClickListener {
            val profileName=intent.getStringExtra("TipoUser")
            val intent = Intent(this,ProxConsultasActivitie::class.java)
            intent.putExtra("TipoUser",profileName)
            startActivity(intent)

        }

        homepagebtLocalizacao.setOnClickListener {
            startActivity(Intent(this,MapsActivity::class.java))
            //startActivity(Intent(this,LocalizacaoActivitie::class.java))
        }

        /*homepagebtOpcoes.setOnClickListener{
            startActivity(Intent(this,opcoes::class.java))
        }*/












    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item?.itemId
            R.id.menu_sign_out
                FirebaseAuth.getInstance().signOut();
                val intent = Intent(this,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)


        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_sign_out, menu)
        return super.onCreateOptionsMenu(menu)
    }
}