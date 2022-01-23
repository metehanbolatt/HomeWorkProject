package com.metehanbolat.homeworkproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.metehanbolat.homeworkproject.databinding.ActivityLogInBinding
import com.metehanbolat.homeworkproject.repository.LogInActivityRepository

class LogInActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {

            logInButton.setOnClickListener {
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                when {
                    email.isBlank() -> Snackbar.make(it, "Please fill the Email Field!", Snackbar.LENGTH_LONG).show()
                    password.isBlank() -> Snackbar.make(it, "Please fill the Password Field!", Snackbar.LENGTH_LONG).show()
                    else -> LogInActivityRepository.userLogin(email, password, it, this@LogInActivity)
                }
            }

            register.setOnClickListener {
                Intent(this@LogInActivity, RegisterActivity::class.java).apply {
                    startActivity(this)
                }

            }

            google.setOnClickListener { Snackbar.make(it, "Google Sign In will be active very soon.", Snackbar.LENGTH_LONG).show() }
            facebook.setOnClickListener { Snackbar.make(it, "Facebook Sign In will be active very soon.", Snackbar.LENGTH_LONG).show() }
            apple.setOnClickListener { Snackbar.make(it, "Apple Sign In will be active very soon.", Snackbar.LENGTH_LONG).show() }

        }

    }
}