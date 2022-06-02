package com.richard.movies.feature_movies.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richard.movies.feature_movies.domain.model.Movie
import com.richard.movies.feature_movies.domain.repository.MoviesRespository
import com.richard.movies.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MoviesRespository
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val detailState: State<MovieDetailState> = _state

    private suspend fun getDetails(movie: Movie) {
        repository.getMovieDetails(movie).collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    if (resource.isLoading) {
                        _state.value = MovieDetailState(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    resource.data?.let {
                        _state.value = MovieDetailState(
                            details = it
                        )
                    }
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(
                        error = true,
                        errorMessage = resource.message ?: "Unknown Message" //Todo Locallise
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            val movie = savedStateHandle.get<Movie>("movie") ?: return@launch
            getDetails(movie)
        }
    }

}