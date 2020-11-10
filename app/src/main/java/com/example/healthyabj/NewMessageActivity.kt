package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_new_message.*


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
        val options = FirestoreRecyclerOptions.Builder<User>().setQuery(query, User::class.java)
            .setLifecycleOwner(this).build()
        val adapter = object : FirestoreRecyclerAdapter<User, UserViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
             val view:View = LayoutInflater.from(this@NewMessageActivity).inflate(
                 R.layout.user_row_new_message,
                 parent,
                 false
             )

                return UserViewHolder(view)

            }


            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: User) {
                val usName: TextView = holder.itemView.findViewById(R.id.usernames)
                usermain.setLateInitVariable( model.name)
                usName.text = model.name

                holder.itemView.setOnClickListener {
                       
                        // Get the selected item text from ListView
//                        val selectedItem = holder.itemView.getItemAtPosition(position) as String

                       val intent = Intent(this@NewMessageActivity, ChatLogActivity::class.java)

                        startActivity(intent)
                }

            }

        }


        recyclerview_newmessage.adapter = adapter
        recyclerview_newmessage.layoutManager = LinearLayoutManager(this)


    }
    companion object{
        val USER_KEY = "USER_KEY"
    }

}



