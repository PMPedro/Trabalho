package com.example.healthyabj

class Chat{
    lateinit var text:String
    lateinit var fromId:String
    lateinit var toId:String
    lateinit var timestamp:String
    data class  ChatMessage(
        val text:String,
        val fromId:String,
        val toId: String,
        val timestamp: Long,



        )

}
