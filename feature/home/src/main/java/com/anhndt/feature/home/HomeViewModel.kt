package com.anhndt.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anhndt.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _getHomeData: MutableStateFlow<GetHomeDataState> =
        MutableStateFlow(GetHomeDataState.Loading)
    val getHomeData: StateFlow<GetHomeDataState> = _getHomeData

    private val _carouselPageIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val carouselPageIndex: StateFlow<Int> = _carouselPageIndex

    private val _carouselPageCount: MutableStateFlow<Int> = MutableStateFlow(0)
    val carouselPageCount: StateFlow<Int> = _carouselPageCount

    init {
        getHomeData()
    }

    private fun getHomeData() = viewModelScope.launch {
        movieRepository.getMovies()
            .flowOn(Dispatchers.IO)
            .onStart { _getHomeData.value = GetHomeDataState.Loading }
            .catch { ex -> _getHomeData.value = GetHomeDataState.Error(message = ex.toString()) }
            .collectLatest { resource ->
                resource.data?.let { movies ->
                    _carouselPageCount.value =
                        if ((resource.data?.size ?: 0) >= 10) 10 else resource.data?.size ?: 0
                    _carouselPageIndex.value = Int.MAX_VALUE / 2
                    _getHomeData.value = GetHomeDataState.Success(movies = movies)
                } ?: run {
                    _carouselPageCount.value = 0
                    _getHomeData.value = GetHomeDataState.Success(movies = emptyList())
                }

            }
    }

}
