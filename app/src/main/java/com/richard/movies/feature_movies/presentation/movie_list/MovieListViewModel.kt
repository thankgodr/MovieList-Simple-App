package com.richard.movies.feature_movies.presentation.movie_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richard.movies.feature_movies.domain.repository.MoviesRespository
import com.richard.movies.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MoviesRespository
) : ViewModel() {


    var state by mutableStateOf(MovieListState())

    fun onEvent(event: MovieListEvent) {
        when (event) {
            is MovieListEvent.Refresh -> {
                getMoviesList(true)
            }
        }
    }

    init {
        getMoviesList()
    }

    private fun getMoviesList(
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getMovieList(fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(
                                movies = it
                            )
                        }
                    }
                    is Resource.Error -> Unit ////Todo Show error
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }

}