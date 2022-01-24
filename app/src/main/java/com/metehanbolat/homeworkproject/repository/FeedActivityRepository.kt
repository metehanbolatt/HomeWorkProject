package com.metehanbolat.homeworkproject.repository

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.metehanbolat.homeworkproject.clients.RetrofitClient
import com.metehanbolat.homeworkproject.models.productmodel.Bilgiler
import com.metehanbolat.homeworkproject.models.productmodel.Products
import com.metehanbolat.homeworkproject.service.RetrofitService
import com.metehanbolat.homeworkproject.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedActivityRepository {

    val data = MutableLiveData<List<Bilgiler>>()

    fun getData(view: View){
        val retrofitService = RetrofitClient.getRetrofitClient().create(RetrofitService::class.java)
        val productService = retrofitService.getProduct()

        productService.enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful){
                    val res = response.body()
                    res?.let { products ->
                        when {
                            products.Products[0].durum.toString() == Constants.TRUE -> {
                                data.value = products.Products[0].bilgiler
                            }
                            products.Products[0].durum.toString() == Constants.FALSE -> Snackbar.make(view, "Log In Failed: ${products.Products[0].mesaj}", Snackbar.LENGTH_LONG).show()
                            else -> Snackbar.make(view, "Unexpected Failure!!", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<Products>, t: Throwable) {
                Snackbar.make(view, "Failed to fetch products : ${t.localizedMessage}", Snackbar.LENGTH_LONG).show()
            }
        })
    }

}