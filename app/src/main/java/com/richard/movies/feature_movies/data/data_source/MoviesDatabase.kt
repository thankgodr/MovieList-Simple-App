package com.richard.movies.feature_movies.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val dao: MoviesDao
}