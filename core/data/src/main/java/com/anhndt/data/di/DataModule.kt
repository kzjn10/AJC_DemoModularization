package com.anhndt.data.di

import com.anhndt.data.repository.MovieRepository
import com.anhndt.data.repository.impl.MovieRepositoryImpl
import com.anhndt.network.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieApi: MovieApi
    ): MovieRepository {
        return MovieRepositoryImpl(movieApi)
    }
}