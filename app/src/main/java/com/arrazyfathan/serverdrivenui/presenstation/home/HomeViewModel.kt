package com.arrazyfathan.serverdrivenui.presenstation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.serverdrivenui.data.datasource.remote.Resources
import com.arrazyfathan.serverdrivenui.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ar Razy Fathan Rabbani on 24/08/23.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _topAppBarUiState = MutableStateFlow(TopAppBarUiState())
    val topAppBarUiState: StateFlow<TopAppBarUiState> get() = _topAppBarUiState

    private val _featuredImageUiState = MutableStateFlow(FeaturedImageUiState())
    val featuredImageUiState: StateFlow<FeaturedImageUiState> get() = _featuredImageUiState

    init {
        getTopAppBarUi()
        getFeaturedImageUi()
    }

    private fun getTopAppBarUi() {
        viewModelScope.launch {
            repository.getTopAppBarUi().collect { resources ->
                when (resources) {
                    is Resources.Success -> {
                        _topAppBarUiState.update {
                            TopAppBarUiState(resources.data)
                        }
                    }

                    is Resources.Failure -> {
                        _topAppBarUiState.update {
                            TopAppBarUiState(null, resources.exception.message)
                        }
                    }
                }
            }
        }
    }

    private fun getFeaturedImageUi() {
        viewModelScope.launch {
            repository.getFeaturedImageUi().collect { resources ->
                when (resources) {
                    is Resources.Success -> {
                        _featuredImageUiState.update {
                            FeaturedImageUiState(resources.data)
                        }
                    }

                    is Resources.Failure -> {
                        _featuredImageUiState.update {
                            FeaturedImageUiState(null, resources.exception.message)
                        }
                    }
                }
            }
        }
    }
}
