package com.example.healthyabj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.perfiluser.*
import kotlinx.android.synthetic.main.signin.*

class PerfilUser : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfiluser)





        fun ShowData () {



            val database = Firebase

            val uid = FirebaseAuth.getInstance().uid
            val ref = FirebaseDatabase.getInstance().getReference("/Users/$uid")


            }
   



//        fun onDataChange(dataSnapshot: DataSnapshot) {
//
//            // This method is called once with the initial value and again
//            // whenever data at this location is updated.
//
//            val uid = FirebaseAuth.getInstance().uid
//            val ref = FirebaseDatabase.getInstance().getReference("/Users/$uid")
//
//            ref.child("name").getValue().toString()
//
//
//            //val value = ref.child("name").getVal1ue()
//
//        }

        //onDataChange()


          var a = mutableListOf<String>()

        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val uid = FirebaseAuth.getInstance().uid
                var post = dataSnapshot.child("/Users/$uid/name").getValue()
                a.set(0,post.toString())


            }


        }

        perfilusertvNome.text = (a.get(0))








    }
}



