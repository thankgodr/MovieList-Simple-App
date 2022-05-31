package com.richard.movies.feature_movies.data.mappers

import com.richard.movies.feature_movies.data.data_source.MovieEntity
import com.richard.movies.feature_movies.data.remote.dto.MovieDetailDto
import com.richard.movies.feature_movies.data.remote.dto.MovieDto
import com.richard.movies.feature_movies.domain.model.Movie
import com.richard.movies.feature_movies.domain.model.MovieDetail

fun MovieEntity.toMovie(): Movie {
    return Movie(
        name = name,
        imageUrl = imageUrl,
        year = year,
        rating = rating,
        movieID = movie_id
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        name = name,
        imageUrl = imageUrl,
        year = year,
        rating = rating,
        movie_id = movieID
    )
}

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        name = title,
        imageUrl = posterPath,
        year = releaseDate,
        rating = "${voteAverage}",
        movie_id = id
    )
}

fun MovieDetailDto.toMovieDtails(movie: Movie): MovieDetail {
    return MovieDetail(
        movie = movie,
        sumary = this.overview
    )
}

fun MovieDetailDto.toMovieDtails(): MovieDetail {
    return MovieDetail(
        movie = Movie(
            name = title,
            imageUrl = posterPath,
            year = releaseDate,
            rating = voteAverage.toString(),
            movieID = id
        ),
        sumary = this.overview
    )
}