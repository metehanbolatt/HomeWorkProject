package com.metehanbolat.homeworkproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.metehanbolat.homeworkproject.adapter.ProductRecyclerAdapter
import com.metehanbolat.homeworkproject.database.ControlDatabase
import com.metehanbolat.homeworkproject.database.Database
import com.metehanbolat.homeworkproject.databinding.ActivityFeedBinding
import com.metehanbolat.homeworkproject.repository.FeedActivityRepository

class FeedActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFeedBinding
    private lateinit var adapter : ProductRecyclerAdapter

    private lateinit var database: ControlDatabase

    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        intent.extras?.let {
            userId = it.getString("userId")
        }

        val repo = FeedActivityRepository()
        database = ControlDatabase(this)

        repo.getData(view)

        repo.data.observe(this) { productBilgiler ->
            userId?.let {
                adapter = ProductRecyclerAdapter(productBilgiler, it, this@FeedActivity)
                binding.recyclerView.adapter = adapter
            }
        }

        binding.apply {
            exitImage.setOnClickListener {
                database.updateUser(1,"null")
                Intent(this@FeedActivity, LogInActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        }

    }

}