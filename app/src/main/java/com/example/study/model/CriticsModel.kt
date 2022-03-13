package com.example.study.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*


@JsonClass(generateAdapter = false)
    data class CriticsResponse(
        val status: String,
        val copyright: String,
        @Json(name = "num_results")
        val numResults: Int,
        @Json(name = "results")
        val critics: List<Critic>?
    )

    @JsonClass(generateAdapter = false)
    data class Critic(
        @Json(name = "display_name")
        val displayName : String,
        @Json(name = "sort_name")
        val sortName : String,
        val status: String,
        val bio : String,
        @Json(name = "seo_name")
        val seoName : String,
        @Json(name = "multimedia")
        val multimedia : MultimediaCritics?
    )

    @JsonClass(generateAdapter = false)
    data class MultimediaCritics(
        @Json(name = "resource")
        val resource: Resource
    )

    @JsonClass(generateAdapter = false)
    data class Resource(
        val type : String,
        val src : String,
        val credit : String
    )


