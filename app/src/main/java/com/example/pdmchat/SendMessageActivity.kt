package com.example.pdmchat

import android.os.Bundle
import android.widget.Toast
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

            if (message.length > 150) {
                Toast.makeText(this, "Mensagem deve ter até 150 caracteres", Toast.LENGTH_SHORT).show()
            } else {
                sendMessage(sender, message)
                finish()
            }
        }
    }

    private fun sendMessage(sender: String, text: String) {
        val timestamp = System.currentTimeMillis()
        val message = Message(sender, text, timestamp)
        database.push().setValue(message)
    }
}
