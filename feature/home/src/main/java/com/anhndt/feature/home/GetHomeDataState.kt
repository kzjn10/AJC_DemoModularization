package com.anhndt.feature.home

import com.anhndt.model.Movie


sealed interface GetHomeDataState {
    object Loading : GetHomeDataState
    data class Success(val movies: List<Movie>?) : GetHomeDataState
    data class Error(val message: String?) : GetHomeDataState
}
