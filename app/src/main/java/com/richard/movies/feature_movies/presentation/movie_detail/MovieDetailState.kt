package com.richard.movies.feature_movies.presentation.movie_detail

import com.richard.movies.feature_movies.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val error: Boolean = false,
    val details: MovieDetail? = null
)
