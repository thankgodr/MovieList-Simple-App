package com.richard.movies.feature_movies.data.data_source

import androidx.room.Database

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MoviesDatabase {
    abstract val dao: MoviesDao
}