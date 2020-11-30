package com.example.healthyabj



class User {

   lateinit var name:String
    lateinit var profileImageUrl:String
    lateinit var uid:String
    data class User(
        val uid: String,
        val email: String,
       val name: String="",
        val password: String,
        val profileImageUrl:String



    )

    fun setLateInitVariable(value: String , valuetwo : String){

        name=value
        profileImageUrl=valuetwo
    }

    fun getLateInitVariable(): String{
        return if(this::name.isInitialized){
            profileImageUrl
            name
        } else {
            "null"
        }
    }
}