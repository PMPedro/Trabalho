package com.example.healthyabj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.chatopage.*
import kotlinx.android.synthetic.main.signin.*

class ChatActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatopage)
        auth = FirebaseAuth.getInstance()


        fun SaveMessage() {


            val database = Firebase

            val uid = FirebaseAuth.getInstance().uid
            val ref = FirebaseDatabase.getInstance().getReference("/Message/")

            val message = Message.Message(chatpageMessage.text.toString(),uid.toString())


            ref.setValue(message)

            // ref.child("users").setValue(users)
            // ref.child("/Users/$uid").setValue(users)
            //ref.child("/Users/$uid").setValue(user)
            //database.child("users").child(userId).setValue(user)
        }


        chatpageSendMessage.setOnClickListener {


            SaveMessage()


            val postListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Get Post object and use the values to update the UI
                    val uid = FirebaseAuth.getInstance().uid
                    var post = dataSnapshot.child("/Message").getValue()

                    chatpagemessageSent.text = post.toString()
                    //  a.set(0,post.toString())

                    chatpageMessage.text.clear()

                }


            }


        }
    }
}