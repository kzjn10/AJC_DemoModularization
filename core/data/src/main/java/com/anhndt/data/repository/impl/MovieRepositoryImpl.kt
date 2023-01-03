package com.anhndt.data.repository.impl

import com.anhndt.common.result.Resource
import com.anhndt.data.model.asData
import com.anhndt.data.repository.MovieRepository
import com.anhndt.model.Movie
import com.anhndt.model.MovieDetail
import com.anhndt.network.BuildConfig
import com.anhndt.network.api.MovieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getMovies(): Flow<Resource<List<Movie>?>> {
        return flow {
            emit(Resource.Loading())
            val result = movieApi.getPopular(key = BuildConfig.API_KEY)
            emit(Resource.Success(result.data?.map { item -> item.asData() }))
        }
    }

    override fun getMovieInfo(id: String): Flow<Resource<MovieDetail?>> {
        return flow {
            emit(Resource.Loading())
            val result = movieApi.getDetail(id, key = BuildConfig.API_KEY)
            emit(Resource.Success(result?.asData()))
        }
    }

}