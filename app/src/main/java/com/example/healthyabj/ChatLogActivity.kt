package com.example.healthyabj

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*


class ChatLogActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    companion object{
        val TAG ="ChatLog"
    }
    val adapter = GroupAdapter<ViewHolder>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)


        recyclerview_chat_log.adapter=adapter

        val username = intent.getStringExtra("Name")
        supportActionBar?.title = username
      // setupDunmmyData()
        val message = findViewById<TextView>(R.id.textView_from_row)
        listenForMessages()
        send_button_chat_log.setOnClickListener{
            Log.d(TAG, "Attemp to send message")
            performSendMessage()
        edittText_chat_log.text.clear()

        }


    }


/*
    private  fun listenForMessages() {
        db.collection("Messages").orderBy("timestamp", Query.Direction.ASCENDING)
            .get()

            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val result: StringBuffer = StringBuffer()
                        var text = String()
                    for (document in task.result) {

                       // text= result.append(document.data.getValue("text")).toString()
                        text =document.data["text"].toString()
                            adapter.add(ChatFromItem( text))




                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }

*/




    private fun listenForMessages() {
        // [START listen_multiple]
        db.collection("Messages")
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
        val text = edittText_chat_log.text.toString()
        val fromId = FirebaseAuth.getInstance().uid
        val userUid = intent.getStringExtra("uid")
        val toId = userUid!!

        if (fromId == null) return

        val refence = FirebaseFirestore.getInstance()

        val chatMessage = Chat.ChatMessage(text, fromId, toId, System.currentTimeMillis() / 1000)


        refence.collection("Messages")
            .add(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "Saved our chat message...${refence}")
            }
        /*if (chatMessage != null) {
            Log.d(TAG, chatMessage.text)
            if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                adapter.add(ChatToItem(chatMessage.text))
            } else {
                adapter.add(ChatFromItem(chatMessage.text))
            }
        }*/
    }
    private fun setupDunmmyData(){
        val adapter = GroupAdapter<ViewHolder>()

        adapter.add(ChatFromItem("From message"))
        adapter.add(ChatToItem("To Message  to message"))


      recyclerview_chat_log.adapter=adapter

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
    private fun TextMessage(message : List<String>) {

    }
}