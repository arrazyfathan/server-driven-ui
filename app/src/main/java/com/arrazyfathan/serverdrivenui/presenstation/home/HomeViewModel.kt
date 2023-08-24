package com.arrazyfathan.serverdrivenui.presenstation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.serverdrivenui.data.datasource.model.CardUi
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

    private val _cardUiState = MutableStateFlow(CardUiState())
    val cardUiState: StateFlow<CardUiState> get() = _cardUiState

    init {
        getCardUi()
    }

    private fun getCardUi() {
        viewModelScope.launch {
            repository.getCardUi().collect { resources ->
                when (resources) {
                    is Resources.Success -> {
                        _cardUiState.update {
                            CardUiState(resources.data)
                        }
                    }

                    is Resources.Failure -> {
                        _cardUiState.update {
                            CardUiState(null, resources.exception.message)
                        }
                    }
                }
            }
        }
    }
}
