package com.richard.movies.feature_movies.data.repository

import com.richard.movies.feature_movies.data.data_source.MoviesDatabase
import com.richard.movies.feature_movies.data.mappers.toMovie
import com.richard.movies.feature_movies.data.mappers.toMovieDtails
import com.richard.movies.feature_movies.data.mappers.toMovieEntity
import com.richard.movies.feature_movies.data.remote.TmdbAPI
import com.richard.movies.feature_movies.domain.model.Movie
import com.richard.movies.feature_movies.domain.model.MovieDetail
import com.richard.movies.feature_movies.domain.repository.MoviesRespository
import com.richard.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: TmdbAPI,
    private val db: MoviesDatabase
) : MoviesRespository {
    private val dao = db.dao

    override suspend fun getMovieList(fetchFromRemote: Boolean): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading<List<Movie>>(true))
            val list = dao.getMovies()
            emit(Resource.Success(
                data = list.map { it.toMovie() }
            ))

            val isDbEmpty = list.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteMovies = try {
                val list = api.getMovies()
                list
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteMovies?.let { dto ->
                dao.insertMovies(
                    dto.results.map { it.toMovieEntity() }
                )
                emit(Resource.Success(
                    data = dao.getMovies().map { it.toMovie() }
                ))

                emit(Resource.Loading<List<Movie>>(false))
            }

        }
    }

    override suspend fun getMovieDetails(movie: Movie): Resource<MovieDetail> {
        return try {
            val response = api.getMovieDatils(movie.movieID.toString())
            Resource.Success(
                response.toMovieDtails()
            )
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

}