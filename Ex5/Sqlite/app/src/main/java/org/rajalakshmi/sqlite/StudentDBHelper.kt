package org.rajalakshmi.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StudentDBHelper(context: Context) : SQLiteOpenHelper(context,
    "StudentDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //TODO("Not yet implemented")
        val createTableQuery = """
 CREATE TABLE Student (
 RollNo TEXT PRIMARY KEY,
 Name TEXT,
 CGPA REAL
);
 """.trimIndent()
        db?.execSQL(createTableQuery)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //TODO("Not yet implemented")
        db?.execSQL("DROP TABLE IF EXISTS Student")
        onCreate(db)
    }
    fun insertStudent(rollNo: String, name: String, cgpa: Double): Boolean { val db = writableDatabase
        val values = ContentValues().apply {
            put("RollNo", rollNo)
            put("Name", name)
            put("CGPA", cgpa)
        }
        val result = db.insert("Student", null, values)
        return result != -1L
    }
    fun updateStudent(rollNo: String, name: String, cgpa: Double): Boolean { val db = writableDatabase
        val values = ContentValues().apply {
            put("Name", name)
            put("CGPA", cgpa)
        }
        val result = db.update("Student", values, "RollNo = ?", arrayOf(rollNo))
        return result > 0
    }
    fun deleteStudent(rollNo: String): Boolean {
        val db = writableDatabase
        val result = db.delete("Student", "RollNo = ?", arrayOf(rollNo))
        return result > 0
    }
    fun getAllStudents(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM Student", null)
    }
}