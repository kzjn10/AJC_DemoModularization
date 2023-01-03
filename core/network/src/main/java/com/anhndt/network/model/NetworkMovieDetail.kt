package com.anhndt.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovieDetail(

    @SerialName("id")
    val id: Int,

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("genres")
    val genres: List<NetworkGenre>?,

    @SerialName("homepage")
    val homePage: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("title")
    val title: String,

    @SerialName("overview")
    val overview: String,

    @SerialName("status")
    val status: String,

    @SerialName("budget")
    val budget: Int,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("video")
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double

)
