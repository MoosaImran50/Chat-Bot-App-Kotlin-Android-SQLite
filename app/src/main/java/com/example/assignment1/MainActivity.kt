package com.example.assignment1

import android.graphics.Color
import android.os.Bundle
import android.util.Log
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db =  DatabaseHelper(this)
        var messagesList = db.readData()

        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyclerView.setBackgroundColor(Color.BLACK)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter(messagesList)

        val textBox = findViewById<EditText>(R.id.myTextBox)
        val sendButton = findViewById<Button>(R.id.mySendButton)
        var enteredMessage = ""

        recyclerView.scrollToPosition(messagesList.size - 1)

        sendButton.setOnClickListener {
            enteredMessage = textBox.text.toString().trim()
            if (enteredMessage == "") {
                Toast.makeText(
                    this@MainActivity,
                    "Text field can not be left blank!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                val message = enteredMessage
                val timeFormat = SimpleDateFormat("h:mm a")
                val currentTime = timeFormat.format(Date())

                messagesList.add(Message("Me", message, currentTime))
                messagesList.add(Message("Bot", "Hello", currentTime))

                textBox.text.clear()
                recyclerView.scrollToPosition(messagesList.size - 1)

                db.addData(Message("Me", message, currentTime))
                db.addData(Message("Bot", "Hello", currentTime))
            }
        }

    }
}