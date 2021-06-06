package com.example.a7minuteworkoutapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

class SqliteOpenHelper(context: Context, factory:SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context, DATABASE_NAME, factory,DATABASE_VERSION) {

    companion object{
        private val DATABASE_VERSION = 5
        private val DATABASE_NAME = "SevenMinutesWorkout.db"
        private val TABLE_HISTORY = "history" //Table name
        private val COLUMN_ID = "_id" // underscore cuz primary key
        private val COLUMN_COMPLETED_DATE = "completed_date"
        private val COLUMN_SELECTED_MOOD = "selected_mood"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_EXERCISE_TABLE = ("CREATE TABLE " + TABLE_HISTORY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_COMPLETED_DATE + " TEXT, " + COLUMN_SELECTED_MOOD + " INTEGER)")
        db?.execSQL(CREATE_EXERCISE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY)
        onCreate(db)
    }

    fun addData(mdc: MyDataClass){
        Log.e("Sqlite", "" + mdc)
        val values = ContentValues()
        values.put(COLUMN_COMPLETED_DATE, mdc.date)
        values.put(COLUMN_SELECTED_MOOD, mdc.img)

        val db = this.writableDatabase
        db.insert(TABLE_HISTORY, null, values)
        db.close()
    }

    fun addSelectedMood(img: Int){
        val values = ContentValues()
        values.put(COLUMN_SELECTED_MOOD, img)

        val db = this.writableDatabase
        db.insert(TABLE_HISTORY, null, values)
        db.close()
    }

    fun getAllDataList() : ArrayList<MyDataClass>{
        val list = ArrayList<MyDataClass>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_HISTORY", null)
        //cursor goes through row in table
        while (cursor.moveToNext()){
            var dateValue = (cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED_DATE)))
            var imgValue = (cursor.getInt(cursor.getColumnIndex(COLUMN_SELECTED_MOOD)))
            Log.e("mySQL", "$dateValue , $imgValue")
            val mdc = MyDataClass(date = dateValue, img = imgValue)
            list.add(mdc)
        }
        cursor.close()
        return list

    }
}