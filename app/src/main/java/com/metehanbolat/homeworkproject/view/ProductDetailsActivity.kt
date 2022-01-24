package com.metehanbolat.homeworkproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.bumptech.glide.Glide
import com.metehanbolat.homeworkproject.databinding.ActivityProductDetailsBinding
import com.metehanbolat.homeworkproject.repository.ProductDetailsActivityRepository

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    private var name: String? = null
    private var image: String? = null
    private var description: String? = null
    private var price: String? = null
    private var id: String? = null
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = ProductDetailsActivityRepository()

        intent.extras?.let {
            name = it.getString("name")
            image = it.getString("image")
            description = it.getString("description")
            price = it.getString("price")
            id = it.getString("id")
            userId = it.getString("userId")
        }

        binding.apply {
            productDescription.movementMethod = ScrollingMovementMethod()
            Glide.with(this@ProductDetailsActivity).load(image).into(productImage)
            productName.text = name
            productDescription.text = description
            productPrice.text = "$price ₺"

            basketButton.setOnClickListener {
                userId?.let { notNullUserId ->
                    id?.let { notNullId ->
                        productImage.visibility = View.INVISIBLE
                        lottieAdd.playAnimation()
                        lottieAdd.visibility = View.VISIBLE
                        basketButton.isClickable = false
                        println(notNullUserId)
                        println(notNullId)
                        repository.addToBasket(notNullUserId, notNullId, view)
                    }
                }

            }

            repository.control.observe(this@ProductDetailsActivity) {
                if (!it){
                    productImage.visibility = View.VISIBLE
                    lottieAdd.visibility = View.INVISIBLE
                    basketButton.isClickable = true
                }
            }
        }


    }
}