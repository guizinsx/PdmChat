package com.example.pdmchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.database.*

class MainActivity : ComponentActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance().reference

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                var message by remember { mutableStateOf("Hello, World!") }
                val messagesList = remember { mutableStateListOf<String>() }

                // Load messages from Firebase
                LaunchedEffect(Unit) {
                    database.child("messages").addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            messagesList.clear()
                            for (data in snapshot.children) {
                                val msg = data.getValue(String::class.java)
                                if (msg != null) {
                                    messagesList.add(msg)
                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Handle database error
                        }
                    })
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Greeting(message)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        writeNewMessage("Hello from Firebase!")
                        message = "Message written to Firebase"
                    }) {
                        Text("Write to Firebase")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    messagesList.forEach {
                        Text(it)
                    }
                }
            }
        }
    }

    private fun writeNewMessage(message: String) {
        database.child("messages").push().setValue(message)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = name)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting("Hello, World!")
    }
}
