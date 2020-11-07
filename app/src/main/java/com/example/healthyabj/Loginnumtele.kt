package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loginnumtele.*
import java.util.concurrent.TimeUnit

class Loginnumtele : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginnumtele)
        auth = FirebaseAuth.getInstance()


        auth.setLanguageCode("pt")


            //Verifica o Callback
        fun VerifyCallbacks () {

         callbacks = object :PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                TODO("Not yet implemented")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                TODO("Not yet implemented")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
            }
        }
        }


        loginnumtelebtVoltar.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }




        //Verifica o numero de telefone, manda sms para fazer a verificação
        fun Verificar() {

            VerifyCallbacks()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            loginnumteleNumTele.text.toString(), // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            callbacks) // OnVerificationStateChangedCallbacks
        }

        //botao verify do louyout, ao clicar envia sms de verificao
        loginnumteleVerificar.setOnClickListener {
                Verificar()
        }



    }





}


