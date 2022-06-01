package com.richard.movies.feature_movies.presentation.movie_list

sealed class MovieListEvent {
    object Refresh : MovieListEvent()
}
