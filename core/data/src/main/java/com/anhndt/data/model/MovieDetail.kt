package com.anhndt.data.model

import com.anhndt.model.MovieDetail
import com.anhndt.network.model.NetworkMovieDetail

fun NetworkMovieDetail.asData() = MovieDetail(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    posterPath = posterPath,
    title = title,
    genres = genres?.map { item -> item.asData() },
    homePage = homePage,
    originalTitle = originalTitle,
    overview = overview,
    status = status,
    budget = budget,
    voteCount = voteCount,
    voteAverage = voteAverage
)

