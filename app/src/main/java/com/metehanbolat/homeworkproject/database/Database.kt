package com.metehanbolat.homeworkproject.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.metehanbolat.homeworkproject.models.usercontrolmodel.UserControl

class Database(context: Context, name: String? = "userProject.db", factory: SQLiteDatabase.CursorFactory? = null, version: Int = 2) :
    SQLiteOpenHelper(context, name, factory, version) {

    private lateinit var userList : ArrayList<UserControl>

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("CREATE TABLE \"user\" (\n" +
                "\t\"uid\"\tINTEGER,\n" +
                "\t\"email\"\tTEXT UNIQUE,\n" +
                "\t\"control\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"uid\" AUTOINCREMENT)\n" +
                ");")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS user")
        onCreate(p0)
    }

    fun addUser(email: String, control: String) {
        val write = this.writableDatabase
        val values = ContentValues()
        values.put("email", email)
        values.put("control", control)
        write.insert("user", null, values)
    }

    fun allUser() : ArrayList<UserControl> {
        userList = ArrayList()
        val read = this.readableDatabase
        val querySql = "select * from user"
        val cursor = read.rawQuery(querySql, null)

        while (cursor.moveToNext()) {
            val uid = cursor.getInt(0)
            val email = cursor.getString(1)
            val control = cursor.getString(2)
            val user = UserControl(uid, email, control)
            userList.add(user)
        }
        return userList
    }

    fun updateUser(uid: Int, email: String, control: String) {
        val write = this.writableDatabase
        val values = ContentValues()
        values.put("email", email)
        values.put("control", control)
        write.update("user", values, "uid=$uid", null)
    }

}