package com.anhndt.data.model

import com.anhndt.model.Movie
import com.anhndt.network.model.NetworkMovie

fun NetworkMovie.asData() = Movie(
    id = id,
    title = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount,
    originalLanguage = originalLanguage
)

