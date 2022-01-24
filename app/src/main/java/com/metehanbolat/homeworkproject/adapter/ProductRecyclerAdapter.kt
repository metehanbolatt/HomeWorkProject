package com.metehanbolat.homeworkproject.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metehanbolat.homeworkproject.databinding.ProductRowBinding
import com.metehanbolat.homeworkproject.models.productmodel.Bilgiler
import com.metehanbolat.homeworkproject.view.ProductDetailsActivity

class ProductRecyclerAdapter(private val productList: List<Bilgiler>,val userId: String, val context: Context) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding : ProductRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.binding.apply {
            Glide.with(context).load(productList[position].images[0].normal).into(productImage)
            productName.text = productList[position].productName
            productPrice.text = "${productList[position].price} ₺"

            productRowLinear.setOnClickListener {
                Intent(context, ProductDetailsActivity::class.java).apply {
                    putExtra("image", productList[position].images[0].normal)
                    putExtra("name", productList[position].productName)
                    putExtra("description", productList[position].description)
                    putExtra("price", productList[position].price)
                    putExtra("id", productList[position].productId)
                    putExtra("userId", userId)
                    context.startActivity(this)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}