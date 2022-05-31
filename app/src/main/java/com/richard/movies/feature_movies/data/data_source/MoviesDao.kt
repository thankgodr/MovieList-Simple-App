package com.richard.movies.feature_movies.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(
        movieEntity: List<MovieEntity>
    )

    @Query("SELECT * FROM movieentity")
    suspend fun getMovies(): List<MovieEntity>
}