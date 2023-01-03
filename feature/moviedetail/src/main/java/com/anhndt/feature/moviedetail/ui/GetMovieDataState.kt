package com.anhndt.feature.moviedetail.ui

import com.anhndt.model.MovieDetail


sealed interface GetMovieDataState {
    object Loading : GetMovieDataState
    data class Success(val movie: MovieDetail?) : GetMovieDataState
    data class Error(val message: String?) : GetMovieDataState
}
