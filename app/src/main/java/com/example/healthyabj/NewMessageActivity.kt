package com.example.healthyabj

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_new_message.*


class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class NewMessageActivity : AppCompatActivity() {

//m lmklm
    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore
    lateinit var textToUid:String
    var Path =db.collection("User")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        supportActionBar?.title = "Select User"

        val usermain = User()



        db.collection("User")
            .addSnapshotListener { value, e ->

                for (doc in value!!) {
                    if (doc.get("uid") == FirebaseAuth.getInstance().currentUser?.uid.toString()) {
                        val usertype = doc.get("usertype").toString()
                         val query = when (usertype) {
                            "0" -> db.collection("User").whereEqualTo("usertype", "1")

                            else -> db.collection("User")
                        }

                        val options = FirestoreRecyclerOptions.Builder<User>().setQuery(query, User::class.java)
                            .setLifecycleOwner(this).build()

                        val adapter = object : FirestoreRecyclerAdapter<User, UserViewHolder>(options) {

                            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
                                val view: View = LayoutInflater.from(this@NewMessageActivity).inflate(
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
                                Picasso.with(this@NewMessageActivity).load(model.profileImageUrl).into(usImage)
                                textToUid = model.uid

                                holder.itemView.setOnClickListener {

                                    val intent = Intent(this@NewMessageActivity, ChatLogActivity::class.java)

                                    intent.putExtra("Name", model.name)
                                    intent.putExtra("uid", model.uid)
                                    intent.putExtra("touid", model.uid)
                                    startActivity(intent)
                                }

                            }

                        }


                        recyclerview_newmessage.adapter = adapter
                        recyclerview_newmessage.layoutManager = LinearLayoutManager(this)











                    }

                }
            }






    }


}




