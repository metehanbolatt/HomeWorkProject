package com.metehanbolat.homeworkproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.metehanbolat.homeworkproject.R
import com.metehanbolat.homeworkproject.database.ControlDatabase
import com.metehanbolat.homeworkproject.database.Database
import com.metehanbolat.homeworkproject.databinding.ActivityEntranceBinding
import com.metehanbolat.homeworkproject.utils.Constants.TRUE

class EntranceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEntranceBinding
    private lateinit var database : Database
    private lateinit var controlDatabase: ControlDatabase
    private var uid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        val view = binding.root
        setTheme(R.style.Theme_HomeWorkProject)
        setContentView(view)

        database = Database(this)
        controlDatabase = ControlDatabase(this)

        object : CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {

                if (passControl()){
                    println(uid)
                    Intent(this@EntranceActivity, FeedActivity::class.java).apply {
                        putExtra("userId", uid.toString())
                        startActivity(this)
                        finish()
                    }
                }else{
                    Intent(this@EntranceActivity, LogInActivity::class.java).apply {
                        startActivity(this)
                        finish()
                    }
                }
            }

        }.start()
    }

    private fun passControl() : Boolean{
        val userList = database.allUser()
        val controlList = controlDatabase.allUser()
        userList.forEach { user ->
            if (user.email == controlList[0].email && user.control == TRUE){
                uid = user.uid
                return true
            }
        }
        return false
    }
}