package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.signin.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)
        var num = 2


        val email =  SignInEmail.text.toString()
        val nome = SignInName.text.toString()
        val password = SignInPassword.text.toString()
        val dataNascimento = signinBirthdate.text.toString()
        val cc = signinCC.text.toString()

        auth= FirebaseAuth.getInstance()


        fun SaveData () {



            val database = Firebase

            val uid = FirebaseAuth.getInstance().uid
            val ref = FirebaseDatabase.getInstance().getReference("/Users/$uid")

            val users = User.User (SignInEmail.text.toString(),SignInPassword.text.toString(),SignInName.text.toString(),signinBirthdate.text.toString(),signinCC.text.toString())


            ref.setValue(users)

           // ref.child("users").setValue(users)
            // ref.child("/Users/$uid").setValue(users)
            //ref.child("/Users/$uid").setValue(user)
            //database.child("users").child(userId).setValue(user)
        }








        //Verifica se alguns dos campos estao vazios, se estiver manda mensagem de erro
        SignInCreateAccount.setOnClickListener {

            if(SignInEmail.text.isEmpty()){
                Toast.makeText(baseContext, "Email em Falta", Toast.LENGTH_SHORT).show()

            }

            else  if(SignInName.text.isEmpty()){
                Toast.makeText(baseContext, "Nome em falta", Toast.LENGTH_SHORT).show()

            }

            else if(SignInPassword.text.isEmpty()){
                Toast.makeText(baseContext, "Password em falta", Toast.LENGTH_SHORT).show()

            }

            else


                auth.createUserWithEmailAndPassword(SignInEmail.text.toString(), SignInPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Caso nao exista nenhum erro ao criar conta, vai para a tela de login
                        // Sign in success, update UI with the signed-in user's information

                        SaveData()
                        val user = auth.currentUser
                        startActivity(Intent(this,MainActivity::class.java))


                     //   SaveData()


                    } else {
                        //Caso exista algum erro ao criar conta, manda mensagem de erro
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Error 404, please try again! ", Toast.LENGTH_SHORT).show()
                    }
                }


        }





    }

}












