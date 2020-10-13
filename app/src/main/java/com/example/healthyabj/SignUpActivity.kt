package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.signin.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)
        auth= FirebaseAuth.getInstance()


        SignInCreateAccount.setOnClickListener {

            if(SignInEmail.text.toString().isEmpty()){
                Toast.makeText(baseContext, "Email em Falta", Toast.LENGTH_SHORT).show()

            }

           else  if(SignInName.text.toString().isEmpty()){
                Toast.makeText(baseContext, "Nome em falta", Toast.LENGTH_SHORT).show()
            }

              else if(SignInPassword.text.toString().isEmpty()){
                Toast.makeText(baseContext, "Password em falta", Toast.LENGTH_SHORT).show()
            }





           else {
                auth.createUserWithEmailAndPassword(SignInEmail.text.toString(), SignInPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Try Again Bich", Toast.LENGTH_SHORT).show()
                    }
                }

                }
        }



    }
}
