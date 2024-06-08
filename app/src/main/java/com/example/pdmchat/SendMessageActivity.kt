package com.example.pdmchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pdmchat.databinding.ActivitySendMessageBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SendMessageActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivitySendMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySendMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = FirebaseDatabase.getInstance().reference.child("messages")


        binding.buttonSend.setOnClickListener {
            val sender = binding.editTextSender.text.toString()
            val message = binding.editTextMessage.text.toString()
            sendMessage(sender, message)
            finish()
        }
    }

    private fun sendMessage(sender: String, text: String) {
        val timestamp = System.currentTimeMillis()
        val message = Message(sender, text, timestamp)
        database.push().setValue(message)
    }
}
