package com.example.assignment1

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment1.databinding.ActivityMain1Binding

class MainActivity1 : AppCompatActivity() {
private lateinit var binding: ActivityMain1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        val db =  DatabaseHelper(this)
        var contactsList = db.readData()

        val recyclerView = findViewById<RecyclerView>(R.id.ContactsRecyclerView)
        recyclerView.setBackgroundColor(Color.BLACK)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter(contactsList)

        recyclerView.scrollToPosition(contactsList.size - 1)
    }
}