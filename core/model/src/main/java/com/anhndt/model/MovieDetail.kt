package com.anhndt.model

data class MovieDetail(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val posterPath: String,
    val title: String,
    val genres: List<Genre>?,
    val homePage: String,
    val originalTitle: String,
    val overview: String,
    val status: String,
    val budget: Int,
    val voteCount: Int,
    val voteAverage: Double
)


