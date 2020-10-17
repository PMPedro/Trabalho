package com.example.healthyabj

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
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

        if (auth.currentUser != null) {
            // User is signed in (getCurrentUser() will be null if not signed in)
            val intent = Intent(this, HomePageActivity::class.java);
            startActivity(intent);
            finish();
        }

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
                    if(task.isSuccessful){

                            if((editTextTextPersonName.text.toString().isEmpty()) || (editTextTextPassword.text.toString().isEmpty())){
                                Toast.makeText(baseContext, "Por favor, insira dados! ", Toast.LENGTH_SHORT).show()
                            }

                    else   {

                        // Se consegui logar com sucesso ele muda de pagina
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "bem vindo", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,HomePageActivity::class.java))
                        finish()
                            }
                    } else {
                        // If sign in fails, display a message to the user.
                        //Se nao conseguir logar com sucesso, manda uma mensagem de erro
                        Toast.makeText(baseContext, "Credenciais Erradas", Toast.LENGTH_SHORT).show()
                        updateUI(null)

                    }


                }

        }

        btn_forgot_password.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dialog_forgot_password,null)
            val username = view.findViewById<EditText>(R.id.et_usermame)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _->
                forgotPassword(username)

            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _->  })
            builder.show()
        }
    }

    private  fun forgotPassword(username:EditText){
        if (username.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            return
        }
        auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                  Toast.makeText(this, "Email sent.",Toast.LENGTH_LONG).show()
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
