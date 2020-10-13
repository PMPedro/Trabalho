package com.example.healthyabj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       auth= FirebaseAuth.getInstance()

        LoginbtSignUp.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }


        LoginntLogin.setOnClickListener{


            auth.signInWithEmailAndPassword(editTextTextPersonName.text.toString(), editTextTextPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        startActivity(Intent(this,medicosactivity::class.java))


                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "PO CRL", Toast.LENGTH_SHORT).show()
                        updateUI(null)

                    }


                }

        }

        LoginbtNumTele.setOnClickListener{
//Ao clicar no botao de Num tele vai para outra tela
            startActivity(Intent(this,loginnumtele::class.java))
            finish()


        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI (curretUser : FirebaseUser?){


    }

}
