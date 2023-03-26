package com.example.assignment1
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.util.jar.Attributes.Name
import kotlin.coroutines.coroutineContext

private const val DATABASE_NAME = "ChatAPP"
private const val TABLE_NAME1 = "Message"
private const val TABLE_NAME2 = "Conversation"

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        val sql1 = "CREATE TABLE " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  "Name TEXT," +
                  "Content TEXT," +
                  "CreationDateTime TEXT)"
        db?.execSQL(sql1)

        val sql2 = "CREATE TABLE " + TABLE_NAME2 + " (Contact_ID INTEGER PRIMARY KEY, " +
                   "Name TEXT)"
        db?.execSQL(sql2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME")
        onCreate(db)
    }

    fun addMessage(msg: Message) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put("Name", msg.name)
        cv.put("Content", msg.message)
        cv.put("CreationDateTime", msg.time_stamp)
        var result = db.insert(TABLE_NAME1, null, cv)
        if (result == -1.toLong()){
            Log.d("DatabaseHelper", "Failed")
        }
        else{
            Log.d("DatabaseHelper", "Success")
        }
    }

    fun readMessage() : MutableList<Message> {
        var messagesList = mutableListOf<Message>()

        val db = this.readableDatabase
        val sql = "Select * from $TABLE_NAME1"
        val result = db.rawQuery(sql, null)
        while (result.moveToNext()){
            var name = result.getString(1)
            var message = result.getString(2)
            var time = result.getString(3)
            messagesList.add(Message(name, message, time))
        }
        result.close()
        db.close()

        return messagesList
    }

    fun readContact() : MutableList<Contact> {
        var contactList = mutableListOf<Contact>()

        val db = this.readableDatabase
        val sql = "Select * from $TABLE_NAME2"
        val result = db.rawQuery(sql, null)
        while (result.moveToNext()){
            var id = result.getString(1).toInt()
            var name = result.getString(2)
            contactList.add(Contact(id, name))
        }
        result.close()
        db.close()

        return contactList
    }

    fun addContact(contact: Contact) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put("Contact_ID", contact.contact_id)
        cv.put("Name", contact.name)
        var result = db.insert(TABLE_NAME2, null, cv)
        if (result == -1.toLong()){
            Log.d("DatabaseHelper", "Failed")
        }
        else{
            Log.d("DatabaseHelper", "Success")
        }
    }
}