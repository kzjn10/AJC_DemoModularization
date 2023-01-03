package com.anhndt.data.repository

import com.anhndt.common.result.Resource
import com.anhndt.model.Movie
import com.anhndt.model.MovieDetail
import com.anhndt.network.model.NetworkMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<Resource<List<Movie>?>>

    fun getMovieInfo(id: String): Flow<Resource<MovieDetail?>>
}