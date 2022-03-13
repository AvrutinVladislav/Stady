package com.example.study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.study.databinding.ActivityMainBinding
import com.example.study.model.ReviewsResponse
import com.example.study.reviews.ReviewsAdapter
import com.example.study.reviews.ReviewsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val reviews: MutableLiveData<ReviewsResponse> = MutableLiveData<ReviewsResponse>()
    val adapter by lazy { ReviewsAdapter.ReviewsListAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.rvReviewsList) {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        getReviews()
        reviews.observe(this) {
            if (it.reviews != null){adapter.reviewsList.addAll(it.reviews)}
            adapter.notifyDataSetChanged()
        }


    }



    fun getReviews() {
        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/movies/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val service = (retrofit.create(ReviewsService.ReviewsService::class.java))

        GlobalScope.launch(Dispatchers.IO) {
            val response = service.getReviews()
            reviews.postValue(response)
        }
    }
}








