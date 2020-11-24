package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.homepage.*

class HomePageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        auth = FirebaseAuth.getInstance()


        homepagebtPerfil.setOnClickListener {
            startActivity(Intent(this,PerfilUser::class.java))

        }


//        homepagebtSignOut.setOnClickListener {
//
//            FirebaseAuth.getInstance().signOut();
//            startActivity(Intent(this,MainActivity::class.java))
//            finish()
//        }

        homepagebtChat.setOnClickListener {
            //vai para o chat
            startActivity(Intent(this,ChatListViewActivity::class.java))
        }


        homepagebtProximasConsultas.setOnClickListener {
            startActivity(Intent(this,ProxConsultasActivitie::class.java))
        }

        homepagebtLocalizacao.setOnClickListener {
            startActivity(Intent(this,LocalizacaoActivitie::class.java))
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