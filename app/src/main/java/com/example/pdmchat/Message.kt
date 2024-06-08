package com.example.pdmchat

data class Message(
    val sender: String = "",
    val recipient: String = "",
    val text: String = "",
    val timestamp: Long = 0L
)
