package com.richard.movies.feature_movies.presentation.movie_list

import com.richard.movies.feature_movies.domain.model.Movie

data class MovieListState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)
