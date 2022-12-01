package com.example.testapp1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?): SQLiteOpenHelper(context, "UserDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE user (\n" +
                "  `id` INT(11) PRIMARY KEY,\n" +
                "  `name` VARCHAR(30),\n" +
                "  `surname` VARCHAR(30)\n" +
                ");"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS user")
        onCreate(db)
    }

    fun insert(tableName: String, dataToInsertArg: ArrayList<Map<String, String>>){
        val db = this.writableDatabase

        val data = ContentValues()

        dataToInsertArg.forEach { elem ->
            elem.forEach { entry ->
                when(entry.value[0]){
                    '_' -> {
                        val newVal: String = entry.value.subSequence(1, entry.value.length).toString()
                        data.put(entry.key, newVal.toInt())
                    }
                    else -> data.put(entry.key, entry.value)
                }
            }
            db.insertOrThrow(tableName, null, data)
        }
        db.close()
    }

    fun getContent(where: String? = null): ArrayList<String> {
        val db = this.writableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM user WHERE ${where ?: "1"};", null)
        val ret: ArrayList<String> = ArrayList()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                ret.add("${cursor.getString(0)} ${cursor.getString(1)} ${cursor.getString(2)}")
            } while(cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return ret
    }
}