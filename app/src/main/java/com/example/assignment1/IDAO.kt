package com.example.assignment1

import android.content.ContentValues
import android.util.Log

interface IDAO {
    // inserting message into database
    fun addMessage(msg: Message)

    // reading message from database
    fun readMessage(id: Int) : MutableList<Message>

    // inserting contact into database
    fun addContact(contact: Contact)

    // reading contact from database
    fun readContact() : MutableList<Contact>

}