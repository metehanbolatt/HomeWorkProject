package com.metehanbolat.homeworkproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.metehanbolat.homeworkproject.database.ControlDatabase
import com.metehanbolat.homeworkproject.database.Database
import com.metehanbolat.homeworkproject.databinding.ActivityLogInBinding
import com.metehanbolat.homeworkproject.repository.LogInActivityRepository
import com.metehanbolat.homeworkproject.utils.Constants
import com.metehanbolat.homeworkproject.utils.Constants.FALSE

class LogInActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLogInBinding

    private lateinit var database: Database
    private lateinit var databaseControl: ControlDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = LogInActivityRepository()
        database = Database(this)
        databaseControl = ControlDatabase(this)

        binding.apply {

            logInButton.setOnClickListener {
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                when {
                    email.isBlank() -> Snackbar.make(it, "Please fill the Email Field!", Snackbar.LENGTH_LONG).show()
                    password.isBlank() -> Snackbar.make(it, "Please fill the Password Field!", Snackbar.LENGTH_LONG).show()
                    else -> {
                        if (checkBox.isChecked) {
                            val userList = database.allUser()
                            userList.forEach { userControl ->
                                if (userControl.email == email && userControl.control != checkBox.isChecked.toString()) {
                                    database.updateUser(userControl.uid, email, Constants.TRUE)
                                }else{
                                    database.addUser(email, Constants.TRUE)
                                }
                            }
                        }else {
                            if (!checkBox.isChecked) {
                                val userList = database.allUser()
                                userList.forEach { userControl ->
                                    if (userControl.email == email && userControl.control != checkBox.isChecked.toString()) {
                                        database.updateUser(userControl.uid, email, FALSE)
                                    }else{
                                        database.addUser(email, FALSE)
                                    }
                                }
                            }
                        }
                        if (databaseControl.allUser().isNullOrEmpty()){
                            databaseControl.addUser(email)
                        }else{
                            databaseControl.updateUser(1, email)
                        }
                        repository.userLogin(email, password, it, this@LogInActivity)
                    }

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