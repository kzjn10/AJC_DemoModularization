package com.anhndt.feature.moviedetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anhndt.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _getMovieData: MutableStateFlow<GetMovieDataState> =
        MutableStateFlow(GetMovieDataState.Loading)
    val getMovieData: StateFlow<GetMovieDataState> = _getMovieData

    fun getMovieInfo(movieId: String) = viewModelScope.launch {
        movieRepository.getMovieInfo(movieId)
            .flowOn(Dispatchers.IO)
            .onStart { _getMovieData.value = GetMovieDataState.Loading }
            .catch { ex -> _getMovieData.value = GetMovieDataState.Error(message = ex.toString()) }
            .collectLatest { resource ->
                resource.data?.let { movie ->
                    _getMovieData.value = GetMovieDataState.Success(movie)
                } ?: run {
                    _getMovieData.value = GetMovieDataState.Success(null)
                }

            }
    }
}