package com.metehanbolat.homeworkproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.metehanbolat.homeworkproject.adapter.ProductRecyclerAdapter
import com.metehanbolat.homeworkproject.databinding.ActivityFeedBinding
import com.metehanbolat.homeworkproject.repository.FeedActivityRepository

class FeedActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFeedBinding
    private lateinit var adapter : ProductRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        FeedActivityRepository.getData(view)

        FeedActivityRepository.data.observe(this) { productBilgiler ->
            adapter = ProductRecyclerAdapter(productBilgiler, this@FeedActivity)
            binding.recyclerView.adapter = adapter
        }

    }

}