package com.example.assignment1
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

private const val DATABASE_NAME = "ChatAPP"
private const val TABLE_NAME1 = "Message"
private const val TABLE_NAME2 = "Conversation"

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        val sql1 = "CREATE TABLE " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  "Contact_ID INTEGER," +
                  "Contact_Name TEXT," +
                  "Content TEXT," +
                  "CreationDateTime TEXT)"
        db?.execSQL(sql1)

        val sql2 = "CREATE TABLE " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY, " +
                   "Name TEXT)"
        db?.execSQL(sql2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME")
        onCreate(db)
    }

    fun addMessage(msg: Message) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("Contact_ID", msg.contact_id)
        cv.put("Contact_Name", msg.name)
        cv.put("Content", msg.message)
        cv.put("CreationDateTime", msg.time_stamp)
        val result = db.insert(TABLE_NAME1, null, cv)
        if (result == -1.toLong()){
            Log.d("DatabaseHelper", "Failed")
        }
        else{
            Log.d("DatabaseHelper", "Success")
        }
    }

    fun readMessage(id: Int) : MutableList<Message> {
        val messagesList = mutableListOf<Message>()

        val db = this.readableDatabase
        val sql = "Select m.Contact_ID, m.Contact_Name, m.Content, m.CreationDateTime from $TABLE_NAME1 as m join $TABLE_NAME2 as c on c.ID = m.Contact_ID where m.Contact_ID = $id"
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

    fun addContact(contact: Contact) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("ID", contact.contact_id)
        cv.put("Name", contact.name)
        val result = db.insert(TABLE_NAME2, null, cv)
        if (result == -1.toLong()){
            Log.d("DatabaseHelper", "Failed")
        }
        else{
            Log.d("DatabaseHelper", "Success")
        }
    }

    fun readContact() : MutableList<Contact> {
        val contactList = mutableListOf<Contact>()
        val db = this.readableDatabase
        val sql = "Select * from $TABLE_NAME2"
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