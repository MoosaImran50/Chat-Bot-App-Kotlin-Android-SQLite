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
private const val TABLE_NAME = "Message"

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  "Name TEXT," +
                  "Content TEXT," +
                  "CreationDateTime TEXT)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME")
        onCreate(db)
    }

    fun addData(msg: Message) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put("Name", msg.name)
        cv.put("Content", msg.message)
        cv.put("CreationDateTime", msg.time_stamp)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()){
            Log.d("DatabaseHelper", "Failed")
        }
        else{
            Log.d("DatabaseHelper", "Success")
        }
    }

    fun readData() : MutableList<Message> {
        var messagesList = mutableListOf<Message>()

        val db = this.readableDatabase
        val sql = "Select * from $TABLE_NAME"
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
}