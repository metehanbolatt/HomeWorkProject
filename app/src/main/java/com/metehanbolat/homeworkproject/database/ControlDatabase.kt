package com.metehanbolat.homeworkproject.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.metehanbolat.homeworkproject.models.controlmodel.ControlUser

class ControlDatabase(context: Context, name: String? = "controlProject.db", factory: SQLiteDatabase.CursorFactory? = null, version: Int = 1) :
    SQLiteOpenHelper(context, name, factory, version) {

    private lateinit var controlUser : ArrayList<ControlUser>

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("CREATE TABLE \"controluser\" (\n" +
                "\t\"uid\"\tINTEGER,\n" +
                "\t\"email\"\tTEXT UNIQUE,\n" +
                "\tPRIMARY KEY(\"uid\")\n" +
                ");")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS controluser")
        onCreate(p0)
    }

    fun addUser(email: String) {
        val write = this.writableDatabase
        val values = ContentValues()
        values.put("email", email)
        write.insert("controluser", null, values)
    }

    fun allUser() : ArrayList<ControlUser> {
        controlUser = ArrayList()
        val read = this.readableDatabase
        val querySql = "select * from controluser"
        val cursor = read.rawQuery(querySql, null)

        while (cursor.moveToNext()) {
            val uid = cursor.getInt(0)
            val email = cursor.getString(1)
            val user = ControlUser(uid, email)
            controlUser.add(user)
        }
        return controlUser
    }

    fun updateUser(uid: Int, email: String) {
        val write = this.writableDatabase
        val values = ContentValues()
        values.put("email", email)
        write.update("controluser", values, "uid=$uid", null)
    }

}