package com.richard.movies.feature_movies.data.remote

import com.richard.movies.feature_movies.data.remote.dto.MovieDetailDto
import com.richard.movies.feature_movies.data.remote.dto.PopalarMoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {

    @GET("3/discover/movie?language=en-US&sort_by=popularity.desc")
    suspend fun getMovies(
        @Query("page") page: Int = 1
    ): PopalarMoviesDto


    suspend fun getMovieDatils(
        @Query("movie_id") movie_id: String
    ): MovieDetailDto

}