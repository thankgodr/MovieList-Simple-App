package com.richard.movies.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.richard.movies.feature_movies.data.data_source.MoviesDatabase
import com.richard.movies.feature_movies.data.remote.TmdbAPI
import com.richard.movies.feature_movies.data.repository.MovieRepositoryImpl
import com.richard.movies.feature_movies.domain.repository.MoviesRespository
import com.richard.movies.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModeule {

    @Provides
    @Singleton
    fun provideTmdbApi(): TmdbAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                        val originalHttpUrl = chain.request().url
                        val newUrl = originalHttpUrl.newBuilder()
                            .addQueryParameter("api_key", Constants.API_KEY)
                            .addQueryParameter("language", "en-US")
                            .build()
                        Log.i("okh", newUrl.toString())
                        request.url(newUrl)
                        chain.proceed(request.build())
                    }.build()

            )
            .build()
            .create(TmdbAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MoviesDatabase {
        return Room.databaseBuilder(
            app,
            MoviesDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepositoryApi(api: TmdbAPI, db: MoviesDatabase): MoviesRespository {
        return MovieRepositoryImpl(api = api, db = db)
    }
}