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

        LoginbtNumTele.setOnClickListener{
//Ao clicar no botao de Num tele vai para outra tela
            startActivity(Intent(this,Loginnumtele::class.java))
            finish()

        }



        LoginntLogin.setOnClickListener{
            auth.signInWithEmailAndPassword(editTextTextPersonName.text.toString(), editTextTextPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        // Se consegui logar com sucesso ele muda de pagina
                        // Sign in success, update UI with the signed-in user's information
                        startActivity(Intent(this,MedicosActivity::class.java))
                        finish()

                    } else {
                        // If sign in fails, display a message to the user.
                        //Se nao conseguir logar com sucesso, manda uma mensagem de erro
                        Toast.makeText(baseContext, "Credenciais Erradas", Toast.LENGTH_SHORT).show()
                        updateUI(null)

                    }


                }

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
