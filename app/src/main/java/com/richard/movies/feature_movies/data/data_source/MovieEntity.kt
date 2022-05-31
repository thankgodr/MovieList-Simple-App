package com.richard.movies.feature_movies.data.data_source

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["movie_id"], unique = true)])
data class MovieEntity(
    val name: String,
    val imageUrl: String,
    val year: String,
    val rating: String,
    val movie_id: Int,
    @PrimaryKey val id: Int? = null
)
