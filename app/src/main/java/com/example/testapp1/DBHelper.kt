package com.example.testapp1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context): SQLiteOpenHelper(context, "UserDB", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        Log.e("abc", "a")
        val query = "CREATE TABLE user (\n" +
                "  `id` INT(11) PRIMARY KEY,\n" +
                "  `name` VARCHAR(30),\n" +
                "  `surname` VARCHAR(30)\n" +
                ")";
        p0?.execSQL(query)
        Log.e("abc", "d")
        Log.e("abc", p0.toString())
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

//    companion object{
//        fun insert(context: Context, dbName: String, dataToInsertArg: Array<Map<String, String>>){
//            val dbHelper = DBHelper(context)
//            val db: SQLiteDatabase = dbHelper.writableDatabase
//
//            val data = ContentValues()
//
//            dataToInsertArg.forEach { elem ->
//                elem.forEach { entry ->
//                    when(entry.value[0]){
//                        '_' -> {
//                            val newVal: String = entry.value.subSequence(1, entry.value.length).toString()
//                            Log.e("test1", "\"${entry.key}\" : \"${newVal.toInt()}\"")
//                            data.put(entry.key, newVal.toInt())
//                            Log.e("test1", "ok")
//                        }
//                        else -> data.put(entry.key, entry.value)
//                    }
//                }
//                Log.e("test1", data.toString())
//                db.insertOrThrow(dbName, null, data)
//                Log.e("test1", "ok3")
//            }
//        }
//    }
}