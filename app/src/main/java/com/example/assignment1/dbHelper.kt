package com.example.assignment1
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

private const val DATABASE_NAME = "ChatAPP"
private const val TABLE_NAME1 = "Message"
private const val TABLE_NAME2 = "Conversation"

class dbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){

    // creating database when application is launched for the first time
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

}