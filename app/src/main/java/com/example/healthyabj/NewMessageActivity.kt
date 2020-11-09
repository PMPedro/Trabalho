package com.example.healthyabj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerAdapter_LifecycleAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.user_row_new_message.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.ArrayList
import kotlin.coroutines.CoroutineContext


class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class NewMessageActivity : AppCompatActivity() {

    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        supportActionBar?.title = "Select User"

        val usermain = User()

       val query: CollectionReference = db.collection("User")
        val options = FirestoreRecyclerOptions.Builder<User>().setQuery(query,User::class.java)
            .setLifecycleOwner(this).build()
        val adapter = object : FirestoreRecyclerAdapter<User, UserViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
             val view:View = LayoutInflater.from(this@NewMessageActivity).inflate(R.layout.user_row_new_message, parent,false)
                return UserViewHolder(view)

            }


            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: User) {
                val usName: TextView = holder.itemView.findViewById(R.id.usernames)



                usermain.setLateInitVariable(model.name)
                usName.text = model.name
                    print(usermain.getLateInitVariable())


            }

        }
        recyclerview_newmessage.adapter = adapter
        recyclerview_newmessage.layoutManager = LinearLayoutManager(this)


    }

}

