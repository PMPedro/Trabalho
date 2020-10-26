package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
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


         fun VerificaUser () {
             if(SignInEmail.text.toString().isEmpty()){
                 Toast.makeText(baseContext, "Email em Falta", Toast.LENGTH_SHORT).show()
                 num = 1
             }

             else  if(SignInName.text.toString().isEmpty()){
                 Toast.makeText(baseContext, "Nome em falta", Toast.LENGTH_SHORT).show()
                 num = 1
             }

             else if(SignInPassword.text.toString().isEmpty()){
                 Toast.makeText(baseContext, "Password em falta", Toast.LENGTH_SHORT).show()
                 num = 1
             }

             else  num = 0

        }

        //Verifica se alguns dos campos estao vazios, se estiver manda mensagem de erro
        SignInCreateAccount.setOnClickListener {
            VerificaUser()







           if ( num === 1 ) {
                auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Caso nao exista nenhum erro ao criar conta, vai para a tela de login
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser

                        startActivity(Intent(this,MainActivity::class.java))

                        finish()

                    } else {
                        //Caso exista algum erro ao criar conta, manda mensagem de erro
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Error 404, please try again! ", Toast.LENGTH_SHORT).show()
                    }
                }

                }
        }

        fun SaveData () {
            val database = Firebase

            val uid = FirebaseAuth.getInstance().uid
            val ref = FirebaseStorage.getInstance().getReference("/Users/$uid")

            val users = User.User (email,password,nome,dataNascimento,cc)

            ref.setvalue(users)
           // ref.child("/Users/$uid").setValue(users)
            //ref.child("/Users/$uid").setValue(user)
            //database.child("users").child(userId).setValue(user)
                    }



    }

}

private fun StorageReference.setvalue(users: User.User) {
    TODO("Not yet implemented")
}









