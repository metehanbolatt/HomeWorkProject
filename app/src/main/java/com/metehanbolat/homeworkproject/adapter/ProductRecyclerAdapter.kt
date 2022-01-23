package com.metehanbolat.homeworkproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metehanbolat.homeworkproject.databinding.ProductRowBinding
import com.metehanbolat.homeworkproject.models.productmodel.Bilgiler

class ProductRecyclerAdapter(private val productList: List<Bilgiler>, val context: Context) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {

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
                println(productList[position].productName)
            }
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}