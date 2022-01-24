package com.metehanbolat.homeworkproject.service

import com.metehanbolat.homeworkproject.models.loginmodel.LogInModel
import com.metehanbolat.homeworkproject.models.ordermodel.Order
import com.metehanbolat.homeworkproject.models.productmodel.Products
import com.metehanbolat.homeworkproject.models.signupmodel.User
import com.metehanbolat.homeworkproject.utils.Constants.HTML
import com.metehanbolat.homeworkproject.utils.Constants.NO
import com.metehanbolat.homeworkproject.utils.Constants.REF
import com.metehanbolat.homeworkproject.utils.Constants.ZERO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("userLogin.php")
    fun userLogin(
        @Query("userEmail") userEmail: String,
        @Query("userPass") userPass: String,
        @Query("ref") ref: String = REF,
        @Query("face") face: String = NO,
    ) : Call<LogInModel>

    @GET("product.php")
    fun getProduct(
        @Query("ref") ref: String = REF,
        @Query("start") start: String = ZERO
    ) : Call<Products>

    @GET("userRegister.php")
    fun userSignUp(
        @Query("userName") userName: String,
        @Query("userSurname") userSurname: String,
        @Query("userPhone") userPhone: String,
        @Query("userMail") userMail: String,
        @Query("userPass") userPass: String,
        @Query("ref") ref: String = REF,
    ) : Call<User>

    @GET("orderForm.php")
    fun addToBasket(
        @Query("customerId") customerId: String,
        @Query("productId") productId: String,
        @Query("html") html: String = HTML,
        @Query("ref") ref: String = REF
    ) : Call<Order>

}