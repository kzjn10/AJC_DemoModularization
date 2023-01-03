package com.anhndt.feature.settings

import androidx.lifecycle.ViewModel
import com.anhndt.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel()