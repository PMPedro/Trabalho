package com.example.healthyabj

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthyabj.MEDICOS.Medicos_Home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.activity_image_send.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.signin.*
import java.util.*

class ImageSendActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var file:String
    companion object{
        val TAG ="ChatLog"
    }

    val adapter = GroupAdapter<ViewHolder>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_send)
        val username = intent.getStringExtra("Name")
        supportActionBar?.title = username

        auth= FirebaseAuth.getInstance()

        ImageSend.setOnClickListener{
            Log.d(ChatLogActivity.TAG,"Try to show photo selector")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent,0)
        }


        buttonSendImage.setOnClickListener{
            Toast.makeText(this@ImageSendActivity, "Escreva uma menssagem", Toast.LENGTH_LONG).show()
            uplaodImageToFirebaseStorage1()

        }

    }
    var selectedPhotoUri: Uri?= null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode,data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data !=null){
            Log.d(TAG," photo selected")
            selectedPhotoUri=data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            ImageSend_imageview.setImageBitmap(bitmap)
            //selectphoto_imageview.rotation = 90
            ImageSend.alpha =0f



        }


    }
    private fun uplaodImageToFirebaseStorage1() {

        if (selectedPhotoUri == null)return
        val filename = UUID.randomUUID().toString()
        val reffoto = FirebaseStorage.getInstance().getReference("/images/$filename")
        reffoto.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d(TAG,"Successfully uploaded image: ${it.metadata?.path}")
                reffoto.downloadUrl.addOnSuccessListener {
                    Log.d(TAG,"File Location $it")
                   file = it.toString()
                    performSendMessage()

                    startActivity(Intent(this,ChatLogActivity::class.java))
                }
            }


    }

    private fun performSendMessage() {


        val text = file
        val fromId = FirebaseAuth.getInstance().uid
        val userUid = intent.getStringExtra("touid")
        var user="QwfhXoa5ULP0nWExjKsc8eVZDuP2"
        val toId = user

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

                Log.d(TAG, "Saved our chat message...${torefence}")
            }

    }

}