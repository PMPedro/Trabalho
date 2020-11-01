package com.example.healthyabj

import java.text.DateFormat

class User {
    constructor()
    lateinit var name:String
    lateinit var email:String
    data class User(
        val email: String,
        var password: String,
        var name: String,
        var datanascimento: String,
        var cc: String
    ) {



        // initializer block

    }


}