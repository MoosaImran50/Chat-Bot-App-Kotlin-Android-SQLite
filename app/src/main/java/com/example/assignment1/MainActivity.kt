package com.example.assignment1

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    var messagesList = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyclerView.setBackgroundColor(Color.BLACK)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter(messagesList)


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
                val sdf = SimpleDateFormat("hh:mm")
                val currentDate = sdf.format(Date())
                messagesList.add(Message("Me", message, currentDate))
                messagesList.add(Message("Bot", "Hello", currentDate))
                textBox.text.clear()
                recyclerView.scrollToPosition(messagesList.size - 1)
            }
        }

    }
}