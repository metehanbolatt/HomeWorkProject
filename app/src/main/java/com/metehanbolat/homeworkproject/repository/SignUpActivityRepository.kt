package com.metehanbolat.homeworkproject.repository

import android.app.Activity
import android.content.Intent
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.metehanbolat.homeworkproject.clients.RetrofitClient
import com.metehanbolat.homeworkproject.models.signupmodel.User
import com.metehanbolat.homeworkproject.service.RetrofitService
import com.metehanbolat.homeworkproject.utils.Constants.FALSE
import com.metehanbolat.homeworkproject.utils.Constants.TRUE
import com.metehanbolat.homeworkproject.view.FeedActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivityRepository {

    fun signUp(userName: String, userSurname: String, userEmail: String, userPassword: String, userPhone: String, view: View, activity : Activity){
        val retrofitService = RetrofitClient.getRetrofitClient().create(RetrofitService::class.java)
        val signUpService = retrofitService.userSignUp(userName, userSurname, userPhone, userEmail, userPassword)

        signUpService.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    val res = response.body()
                    res?.let { user ->
                        when {
                            user.user[0].durum.toString() == TRUE -> {
                                Intent(activity, FeedActivity::class.java).apply {
                                    putExtra("userId", user.user[0].userId)
                                    activity.startActivity(this)
                                    activity.finish()
                                }
                            }
                            user.user[0].durum.toString() == FALSE -> Snackbar.make(view, "Sign Up Failed: ${user.user[0].mesaj}", Snackbar.LENGTH_LONG).show()
                            else -> Snackbar.make(view, "Unexpected Failure!!", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Snackbar.make(view, "Sign Up Failed :  ${t.localizedMessage}", Snackbar.LENGTH_INDEFINITE).setAction("Close",null).show()
            }
        })
    }
}