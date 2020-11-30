package com.example.healthyabj

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthyabj.ChatLogActivity.Companion.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class minhasconsultaslistview : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.minhasconsultaslistview)

        val db = FirebaseFirestore.getInstance()


        db.collection("ConsultasExis")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val result: StringBuffer = StringBuffer()

                    for (document in task.result) {
                       result.append(document.data.getValue("Message"))




                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }


    }

}