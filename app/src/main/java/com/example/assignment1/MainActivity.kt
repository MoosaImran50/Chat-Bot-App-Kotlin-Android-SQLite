package com.example.assignment1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var messagesList = mutableListOf<Message>(Message("Moosa", "Honda"),
        Message("Anonymous", "Toyota"),
        Message("Moosa", "KIA"),
        Message("Anonymous", "Suzuki"),
        Message("Moosa", "Honda"),
        Message("Anonymous", "Suzuki"),
        Message("Moosa", "Suzuki"),
        Message("Anonymous", "Toyota"),
        Message("Moosa", "Toyota"),
        Message("Anonymous Cruiser", "Toyota"),
        Message("Moosa", "Honda"),
        Message("Anonymous", "KIA"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyclerView.setBackgroundColor(Color.BLACK)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter(messagesList)
        recyclerView.scrollToPosition(messagesList.size - 1)


        val textBox = findViewById<EditText>(R.id.myTextBox)
        val sendButton = findViewById<Button>(R.id.mySendButton)
        var enteredMessage = ""
        sendButton.setOnClickListener {
            enteredMessage = textBox.text.toString()
            if (enteredMessage == "") {
                Toast.makeText(
                    this@MainActivity,
                    "Name field can not be left blank!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                val message = enteredMessage
                messagesList.add(Message("Ziyad", message))
                messagesList.add(Message("Anas", "Fok off nigga!"))
                recyclerView.scrollToPosition(messagesList.size - 1)
                textBox.text.clear()
            }
        }

    }
}