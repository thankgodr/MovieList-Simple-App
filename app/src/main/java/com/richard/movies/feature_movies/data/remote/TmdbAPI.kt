package com.richard.movies.feature_movies.data.remote

import com.richard.movies.feature_movies.data.remote.dto.MovieDetailDto
import com.richard.movies.feature_movies.data.remote.dto.PopalarMoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {

    @GET("discover/movie?language=en-US&sort_by=popularity.desc")
    suspend fun getMovies(
        @Query("page") page: Int = 1
    ): PopalarMoviesDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDatils(
        @Path("movie_id") movie_id: String
    ): MovieDetailDto

}