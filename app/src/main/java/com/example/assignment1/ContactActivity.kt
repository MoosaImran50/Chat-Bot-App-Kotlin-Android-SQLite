package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.contact_main)

        val db =  DatabaseHelper(this)
        val contactsList = db.readContact()

        val recyclerView = findViewById<RecyclerView>(R.id.ContactsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        val adapter = MyContactRecyclerViewAdapter(contactsList)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: MyContactRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@ContactActivity, ConversationActivity::class.java)
                intent.putExtra("ID", contactsList[position].contact_id)
                intent.putExtra("NAME", contactsList[position].name)
                startActivity(intent)
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
                    this@ContactActivity,
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