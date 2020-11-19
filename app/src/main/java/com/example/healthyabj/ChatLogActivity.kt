package com.example.healthyabj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*



class ChatLogActivity : AppCompatActivity() {
    companion object{
        val TAG ="ChatLog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)


        
        val username = intent.getStringExtra("Name")
        supportActionBar?.title = username
      //  setupDunmmyData()
        listenForMessages()
        send_button_chat_log.setOnClickListener{
            Log.d(TAG,"Attemp to send message")
            performSendMessage()
        }
    }
    private  fun listenForMessages(){
        val ref=FirebaseFirestore.getInstance().collection("Messages")

        
    }
    class  ChatMessage( val text:String, /*val fromId:String,val toId: String*/ )

    private fun performSendMessage(){
        val text =edittText_chat_log.text.toString()
        val fromId= FirebaseAuth.getInstance().uid
        val userUid = intent.getStringExtra("uid")
        val toId= userUid

        if (fromId == null)return

        val refence= FirebaseFirestore.getInstance()

       val chatMessage=ChatMessage(text)


        refence.collection("Messages")
           .add(chatMessage)
          .addOnSuccessListener {
                Log.d(TAG,"Saved our chat message...${refence}")
            }

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
}