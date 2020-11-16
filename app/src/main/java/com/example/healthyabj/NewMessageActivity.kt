package com.example.healthyabj

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.aboutus.view.*
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.user_row_new_message.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*


class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class NewMessageActivity : AppCompatActivity() {


    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        supportActionBar?.title = "Select User"

        val usermain = User()


        val query = db.collection("User")
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
                val usImage: ImageView = holder.itemView.findViewById(R.id.imageViewUser)



                usName.text = model.name
                var uid = model.uid
             Picasso.with(this@NewMessageActivity).load(model.profileImageUrl).into(usImage)


                holder.itemView.setOnClickListener {

                    var name = usName.text.toString()
                    var uid =  uid
                    val intent = Intent(this@NewMessageActivity, ChatLogActivity::class.java)
                    intent.putExtra("Name", name)
                    intent.putExtra("uid", uid)

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



