package com.metehanbolat.homeworkproject.repository

import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.airbnb.lottie.Lottie
import com.google.android.material.snackbar.Snackbar
import com.metehanbolat.homeworkproject.clients.RetrofitClient
import com.metehanbolat.homeworkproject.models.ordermodel.Order
import com.metehanbolat.homeworkproject.service.RetrofitService
import com.metehanbolat.homeworkproject.utils.Constants.FALSE
import com.metehanbolat.homeworkproject.utils.Constants.TRUE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailsActivityRepository {

    var control = MutableLiveData(true)

    fun addToBasket(customerId: String, productId: String, view: View) {
        val retrofitService = RetrofitClient.getRetrofitClient().create(RetrofitService::class.java)
        val basketService = retrofitService.addToBasket(customerId, productId)

        basketService.enqueue(object : Callback<Order> {
            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    res?.let { order ->
                        when {
                            order.order[0].durum.toString() == TRUE -> {
                                object : CountDownTimer(2500, 1000) {
                                    override fun onTick(p0: Long) {}

                                    override fun onFinish() {
                                        control.value = false
                                    }

                                }.start()
                                Snackbar.make(view, "Successfully Ordered", Snackbar.LENGTH_LONG).show()
                            }
                            order.order[0].durum.toString() == FALSE -> Snackbar.make(view, "Add Failed: ${order.order[0].mesaj}", Snackbar.LENGTH_LONG).show()
                            else -> Snackbar.make(view, "Unexpected Failure!!", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<Order>, t: Throwable) {
                Snackbar.make(view, "Add Failed :  ${t.localizedMessage}", Snackbar.LENGTH_INDEFINITE).setAction("Close",null).show()
            }

        })
    }
}