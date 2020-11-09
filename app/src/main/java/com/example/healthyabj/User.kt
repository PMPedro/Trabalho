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

class User {

   lateinit var name:String
    lateinit var email:String
    lateinit var uid:String
    data class User(
        val uid: String,
        val email: String,
       val name: String="",
        val passwnameord: String



    )

    fun setLateInitVariable(value: String){
        name=value
    }

    fun getLateInitVariable(): String{
        return if(this::name.isInitialized){
            name
        } else {
            "null"
        }
    }
}