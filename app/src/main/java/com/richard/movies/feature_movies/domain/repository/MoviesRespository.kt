package com.richard.movies.feature_movies.domain.repository

import com.richard.movies.feature_movies.domain.model.Movie
import com.richard.movies.feature_movies.domain.model.MovieDetail
import com.richard.movies.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRespository {
    suspend fun getMovieList(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovieDetails(movie: Movie): Flow<Resource<MovieDetail>>
}