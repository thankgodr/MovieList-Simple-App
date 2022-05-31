package com.richard.movies.feature_movies.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PopalarMoviesDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)