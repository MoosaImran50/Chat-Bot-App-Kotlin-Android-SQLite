package com.example.assignment1

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment1.databinding.ActivityMain1Binding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity1 : AppCompatActivity() {
private lateinit var binding: ActivityMain1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        val db =  DatabaseHelper(this)
        var contactsList = db.readContact()

        val recyclerView = findViewById<RecyclerView>(R.id.ContactsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        val adapter = MyContactRecyclerViewAdapter(contactsList)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: MyContactRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    this@MainActivity1,
                    contactsList[position].name,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        recyclerView.scrollToPosition(contactsList.size - 1)

        val textBox = findViewById<EditText>(R.id.myContactTextBox)
        val addButton = findViewById<Button>(R.id.addButton)
        var enteredName = ""

        recyclerView.scrollToPosition(contactsList.size - 1)

        addButton.setOnClickListener {
            enteredName = textBox.text.toString().trim()
            if (enteredName == "") {
                Toast.makeText(
                    this@MainActivity1,
                    "Text field can not be left blank!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                val id = contactsList.size
                val name = enteredName

                contactsList.add(Contact(id, name))

                textBox.text.clear()
                recyclerView.scrollToPosition(contactsList.size - 1)

                db.addContact(Contact(id, name))
            }

        }
    }
}