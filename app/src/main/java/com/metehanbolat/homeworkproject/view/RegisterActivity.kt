package com.metehanbolat.homeworkproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.metehanbolat.homeworkproject.databinding.ActivityRegisterBinding
import com.metehanbolat.homeworkproject.repository.SignUpActivityRepository

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {

            backButton.setOnClickListener {
                val intent = Intent(this@RegisterActivity, LogInActivity::class.java)
                startActivity(intent)
            }

            signUpButton.setOnClickListener {
                val repository = SignUpActivityRepository()
                val name = binding.name.text.toString()
                val surname = binding.surname.text.toString()
                val phone = binding.phone.text.toString()
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()

                when {
                    name.isBlank() -> Snackbar.make(it, "Please fill the Name Field!", Snackbar.LENGTH_LONG).show()
                    surname.isBlank() -> Snackbar.make(it, "Please fill the Surname Field!", Snackbar.LENGTH_LONG).show()
                    phone.isBlank() -> Snackbar.make(it, "Please fill the Phone Field!", Snackbar.LENGTH_LONG).show()
                    email.isBlank() -> Snackbar.make(it, "Please fill the Email Field!", Snackbar.LENGTH_LONG).show()
                    password.isBlank() -> Snackbar.make(it, "Please fill the Password Field!", Snackbar.LENGTH_LONG).show()
                    else -> repository.signUp(name, surname, email, password, phone, it, this@RegisterActivity)
                }
            }

        }

    }
}