package com.example.assignment1
import android.content.ContentValues
import android.content.Context
import android.util.Log

class dbDAO(private val context: Context): IDAO {
    // inserting message into database
    override fun addMessage(msg: Message) {
        val instance = dbHelper(context)
        val db = instance.writableDatabase
        val cv = ContentValues()
        cv.put("Contact_ID", msg.contact_id)
        cv.put("Contact_Name", msg.name)
        cv.put("Content", msg.message)
        cv.put("CreationDateTime", msg.time_stamp)
        val result = db.insert("Message", null, cv)
        if (result == -1.toLong()){
            Log.d("DatabaseHelper", "Failed")
        }
        else{
            Log.d("DatabaseHelper", "Success")
        }
    }

    // reading message from database
    override fun readMessage(id: Int) : MutableList<Message> {
        val messagesList = mutableListOf<Message>()

        val instance = dbHelper(context)
        val db = instance.readableDatabase
        val sql = "Select m.Contact_ID, m.Contact_Name, m.Content, m.CreationDateTime from Message as m join Conversation as c on c.ID = m.Contact_ID where m.Contact_ID = $id"
        val result = db.rawQuery(sql, null)
        while (result.moveToNext()){
            val contactID = result.getString(0).toInt()
            val name = result.getString(1)
            val message = result.getString(2)
            val timeStamp = result.getString(3)
            messagesList.add(Message(contactID, name, message, timeStamp))
        }
        result.close()
        db.close()

        return messagesList
    }

    // inserting contact into database
    override fun addContact(contact: Contact) {
        val instance = dbHelper(context)
        val db = instance.writableDatabase
        val cv = ContentValues()
        cv.put("ID", contact.contact_id)
        cv.put("Name", contact.name)
        val result = db.insert("Conversation", null, cv)
        if (result == -1.toLong()){
            Log.d("DatabaseHelper", "Failed")
        }
        else{
            Log.d("DatabaseHelper", "Success")
        }
    }

    // reading contact from database
    override fun readContact() : MutableList<Contact> {
        val contactList = mutableListOf<Contact>()

        val instance = dbHelper(context)
        val db = instance.readableDatabase
        val sql = "Select * from Conversation"
        val result = db.rawQuery(sql, null)

        while (result.moveToNext()){
            val id = result.getString(0).toInt()
            val name = result.getString(1)

            contactList.add(Contact(id, name))
        }
        result.close()
        db.close()

        return contactList
    }

}