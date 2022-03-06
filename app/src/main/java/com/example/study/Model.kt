package com.example.study

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

object Model {
    @JsonClass(generateAdapter = false)
    data class ReviewsResponse(
        @Json(name = "status")
        val status: String,
        @Json(name = "copyright")
        val copyright: String,
        @Json(name = "has_more")
        val hasMore: Boolean,
        @Json(name = "num_results")
        val numResults: Int,
        @Json(name = "results")
        val reviews: List<Review>?
    )

    @JsonClass(generateAdapter = false)
    data class Review(
        @Json(name = "display_title")
        val displayTitle: String,
        @Json(name = "byline")
        val byline: String,
        @Json(name = "summary_short")
        val summaryShort: String,
        @Json(name = "publication_date")
        val publicationDate: String,
        @Json(name = "date_updated")
        val dateUpdated: String,
        val link: Link,
        val multimedia: Multimedia?
    )

    @JsonClass(generateAdapter = false)
    data class Link(
        val url: String
    )

    @JsonClass(generateAdapter = false)
    data class Multimedia(
        val src: String?
    )

}