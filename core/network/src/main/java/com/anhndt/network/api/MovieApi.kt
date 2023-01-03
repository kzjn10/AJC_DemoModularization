package com.anhndt.network.api

import com.anhndt.network.model.NetworkMovie
import com.anhndt.network.model.NetworkMovieDetail
import com.anhndt.network.response.BaseListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET(value = "movie/popular")
    suspend fun getPopular(@Query("api_key") key: String): BaseListResponse<List<NetworkMovie>?>

    @GET(value = "movie/top_rated")
    suspend fun getTopRated(@Query("api_key") key: String): BaseListResponse<List<NetworkMovie>?>

    @GET(value = "movie/upcoming")
    suspend fun getUpcoming(@Query("api_key") key: String): BaseListResponse<List<NetworkMovie>?>

    @GET(value = "movie/{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movieId: String,
        @Query("api_key") key: String
    ): NetworkMovieDetail?
}