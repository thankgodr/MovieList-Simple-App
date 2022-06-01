package com.richard.movies.feature_movies.domain.model

import com.richard.movies.utils.Constants

data class Movie(
    val name: String,
    val imageUrl: String,
    val year: String,
    val rating: String,
    val movieID: Int
) {
    fun getFullImagePath(): String {
        return Constants.BASE_URL + imageUrl
    }
}
