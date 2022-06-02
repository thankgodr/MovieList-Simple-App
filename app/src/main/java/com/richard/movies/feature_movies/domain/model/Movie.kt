package com.richard.movies.feature_movies.domain.model

import android.os.Parcelable
import com.richard.movies.utils.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val imageUrl: String,
    val year: String,
    val rating: String,
    val movieID: Int
) : Parcelable {
    fun getFullImagePath(): String {
        return Constants.IMGAE_URL + imageUrl
    }
}
