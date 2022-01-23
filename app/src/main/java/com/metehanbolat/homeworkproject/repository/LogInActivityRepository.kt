package com.metehanbolat.homeworkproject.repository

import android.app.Activity
import android.content.Intent
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.metehanbolat.homeworkproject.clients.RetrofitClient
import com.metehanbolat.homeworkproject.models.loginmodel.LogInModel
import com.metehanbolat.homeworkproject.service.RetrofitService
import com.metehanbolat.homeworkproject.utils.Constants.FALSE
import com.metehanbolat.homeworkproject.utils.Constants.TRUE
import com.metehanbolat.homeworkproject.view.FeedActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LogInActivityRepository {

    fun userLogin(email : String, password: String, view: View, activity: Activity) {
        val retrofitService = RetrofitClient.getRetrofitClient().create(RetrofitService::class.java)
        val logInService = retrofitService.userLogin(email, password)

        logInService.enqueue(object : Callback<LogInModel> {
            override fun onResponse(call: Call<LogInModel>, response: Response<LogInModel>) {
                if (response.isSuccessful){
                    val res = response.body()
                    res?.let { logIn ->
                        when {
                            logIn.user[0].durum.toString() == TRUE -> {
                                Intent(activity, FeedActivity::class.java).apply {
                                    putExtra("name", logIn.user[0].bilgiler.userName)
                                    activity.startActivity(this)
                                    activity.finish()
                                }
                            }
                            logIn.user[0].durum.toString() == FALSE -> Snackbar.make(view, "Log In Failed: ${logIn.user[0].mesaj}", Snackbar.LENGTH_LONG).show()
                            else -> Snackbar.make(view, "Unexpected Failure!!", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<LogInModel>, t: Throwable) {
                Snackbar.make(view, "Log In Failed :  ${t.localizedMessage}", Snackbar.LENGTH_INDEFINITE).setAction("Close",null).show()
            }
        })
    }
}