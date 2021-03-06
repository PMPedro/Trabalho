package com.example.healthyabj

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*
import java.util.*


class ChatLogActivity : AppCompatActivity() {
   // val message : MutableMap<String,Any> = mutableMapOf()
    val db = FirebaseFirestore.getInstance()
    companion object{
        val TAG ="ChatLog"
    }
    lateinit var file:String
    val adapter = GroupAdapter<ViewHolder>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

            recyclerview_chat_log.adapter=adapter
        val username = intent.getStringExtra("Name")
        supportActionBar?.title = username




        listenForMessages()
        send_button_chat_log.setOnClickListener{
            var emty = edittText_chat_log.text.toString()
            if (emty == "") {
                Toast.makeText(this@ChatLogActivity, "Escreva uma menssagem", Toast.LENGTH_LONG).show()
            }else{
                Log.d(TAG, "Attemp to send message")
                performSendMessage()
                recyclerview_chat_log.focusSearch(ScrollView.FOCUS_DOWN)
            }
            edittText_chat_log.text.clear()

        }


    }

    var selectedPhotoUri: Uri?= null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode,data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data !=null){
            Log.d(ImageSendActivity.TAG," photo selected")
            selectedPhotoUri=data?.data
            uplaodImageToFirebaseStorage()


        }


    }
    private fun uplaodImageToFirebaseStorage() {

        if (selectedPhotoUri == null)return
        val filename = UUID.randomUUID().toString()
        val reffoto = FirebaseStorage.getInstance().getReference("/images/$filename")
        reffoto.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d(ImageSendActivity.TAG,"Successfully uploaded image: ${it.metadata?.path}")
                reffoto.downloadUrl.addOnSuccessListener {
                    Log.d(ImageSendActivity.TAG,"File Location $it")
                    file = it.toString()
                    performSendFoto()


                }
            }

    }

    private fun listenForMessages() {
        // [START listen_multiple]
        adapter.clear()
        recyclerview_chat_log.focusSearch(ScrollView.FOCUS_DOWN)
        val fromId= FirebaseAuth.getInstance().uid
        val toId  = intent.getStringExtra("uid")
        db.collection("User-Messages/$fromId/$toId")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { value, e ->

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                val result: StringBuffer = StringBuffer()
                var text = String()
                var from = String()

                for (doc in value!!) {
                    text = doc.get("text").toString()

                    from = doc.get("fromId").toString()

                    if(from ==FirebaseAuth.getInstance().uid){
                        adapter.add(ChatFromItem(text))


                    }else{
                        adapter.add(ChatToItem(text))



                    }
                }
             }

            }

    private fun performSendMessage() {
        adapter.clear()

        val text = edittText_chat_log.text.toString()
        val fromId = FirebaseAuth.getInstance().uid
        val userUid = intent.getStringExtra("uid")
        val toId = userUid!!

        if (fromId == null) return

        val refence = FirebaseFirestore.getInstance()
        val torefence = FirebaseFirestore.getInstance()

        val chatMessage = Chat.ChatMessage(text, fromId, toId, System.currentTimeMillis() / 1000)

        refence.collection("User-Messages/$fromId/$toId")
            .add(chatMessage)
            .addOnSuccessListener {

                Log.d(TAG, "Saved our chat message...${refence}")

            }
        torefence.collection("User-Messages/$toId/$fromId")
            .add(chatMessage)

            .addOnSuccessListener {

                Log.d(TAG, "Saved our chat message...${torefence}")
            }

    }


    private fun performSendFoto() {

        val text = file
        val fromId = FirebaseAuth.getInstance().uid
        val userUid = intent.getStringExtra("touid")
        val toId = userUid!!
        if (fromId == null) return

        val refence = FirebaseFirestore.getInstance()
        val torefence = FirebaseFirestore.getInstance()

        val chatMessage = Chat.ChatMessage(text, fromId, toId, System.currentTimeMillis() / 1000)
        adapter.clear()
        refence.collection("User-Messages/$fromId/$toId")
            .add(chatMessage)
            .addOnSuccessListener {

                Log.d(TAG, "Saved our chat message...${refence}")

            }
        torefence.collection("User-Messages/$toId/$fromId")
            .add(chatMessage)

            .addOnSuccessListener {

                Log.d(ImageSendActivity.TAG, "Saved our chat message...${torefence}")
            }

    }

    class  ChatFromItem(val text: String) : Item<ViewHolder>(){

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.textView_from_row.text=text

        }
        override fun getLayout(): Int {
            return R.layout.chat_from_row
        }

    }
    class  ChatToItem(val text: String)  : Item<ViewHolder>(){
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.textView_to_row.text=text

        }
        override fun getLayout(): Int {
            return R.layout.chat_to_row
        }


    }

}