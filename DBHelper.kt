package com.example.studentmanagement

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "StudentDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE students (roll INTEGER PRIMARY KEY, name TEXT, marks REAL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS students")
        onCreate(db)
    }

    fun insertStudent(roll: Int, name: String, marks: Double): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("roll", roll)
        values.put("name", name)
        values.put("marks", marks)
        val result = db.insert("students", null, values)
        return result != -1L
    }

    fun getAllStudents(): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM students", null)
        var data = ""

        while (cursor.moveToNext()) {
            data += "Roll: ${cursor.getInt(0)}  Name: ${cursor.getString(1)}  Marks: ${cursor.getDouble(2)}\n"
        }

        cursor.close()
        return if (data.isEmpty()) "No Data Found" else data
    }

    fun updateStudent(roll: Int, name: String, marks: Double): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("marks", marks)
        val result = db.update("students", values, "roll=?", arrayOf(roll.toString()))
        return result > 0
    }

    fun deleteStudent(roll: Int): Boolean {
        val db = writableDatabase
        val result = db.delete("students", "roll=?", arrayOf(roll.toString()))
        return result > 0
    }
}
