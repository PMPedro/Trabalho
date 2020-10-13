package com.example.healthyabj

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.signup_numtele.*

class SignUpNumTele : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)
        auth = FirebaseAuth.getInstance()



        signupnumteleCB.setOnClickListener {
            Toast.makeText(baseContext, "I agree that Pedro can call-me everyday of the week (if you a girl)!", Toast.LENGTH_LONG).show()

        }

        signupnumteleAddNum.setOnClickListener {
            if(signupnumteleCB.isChecked()){
                // Fazer cenas para adicionar num tele e guardalo na bd
            }

            else
            {
                //Se a checkbox n tiver clicada n da para dar criar novo num de tele 
                Toast.makeText(baseContext, "Please click on the CheckBox", Toast.LENGTH_LONG).show()
                finish()
            }



        }
    }


}