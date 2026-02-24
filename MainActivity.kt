package com.example.studentmanagement

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)

        val etName = findViewById<EditText>(R.id.etName)
        val etRoll = findViewById<EditText>(R.id.etRoll)
        val etMarks = findViewById<EditText>(R.id.etMarks)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        findViewById<Button>(R.id.btnInsert).setOnClickListener {
            val name = etName.text.toString()
            val roll = etRoll.text.toString().toInt()
            val marks = etMarks.text.toString().toDouble()

            val success = dbHelper.insertStudent(roll, name, marks)
            tvResult.text = if (success) "Inserted Successfully" else "Insert Failed"
        }

        findViewById<Button>(R.id.btnView).setOnClickListener {
            tvResult.text = dbHelper.getAllStudents()
        }

        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            val name = etName.text.toString()
            val roll = etRoll.text.toString().toInt()
            val marks = etMarks.text.toString().toDouble()

            val success = dbHelper.updateStudent(roll, name, marks)
            tvResult.text = if (success) "Updated Successfully" else "Update Failed"
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val roll = etRoll.text.toString().toInt()
            val success = dbHelper.deleteStudent(roll)
            tvResult.text = if (success) "Deleted Successfully" else "Delete Failed"
        }
    }
}
