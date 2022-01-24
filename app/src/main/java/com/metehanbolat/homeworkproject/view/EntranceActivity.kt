package com.metehanbolat.homeworkproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.metehanbolat.homeworkproject.R
import com.metehanbolat.homeworkproject.databinding.ActivityEntranceBinding

class EntranceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEntranceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        val view = binding.root
        setTheme(R.style.Theme_HomeWorkProject)
        setContentView(view)

        object : CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                val intent = Intent(baseContext, LogInActivity::class.java)
                startActivity(intent)
                finish()
            }

        }.start()
    }
}