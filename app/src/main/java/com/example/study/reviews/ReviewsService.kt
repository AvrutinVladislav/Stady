package com.example.study.reviews


import com.example.study.model.ReviewsResponse
import retrofit2.http.GET

interface ReviewsService {
    interface ReviewsService {
        @GET("reviews/all.json?api-key=cqmVWmORvGkA3iKgGX7URNzutvC3RrQS")
        suspend fun getReviews(): ReviewsResponse
    }
}