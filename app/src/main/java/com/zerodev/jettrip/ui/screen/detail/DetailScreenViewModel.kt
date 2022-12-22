package com.zerodev.jettrip.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.jettrip.model.Trip
import com.zerodev.jettrip.repository.TripRepository
import com.zerodev.jettrip.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    private val tripRepository: TripRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Trip>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Trip>> get() = _uiState

    fun getTripById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(tripRepository.getTripById(id))
        }
    }

    fun addToFavorite(id: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            tripRepository.addToFavorite(id, !isFavorite).collect { isFavorite ->
                if (isFavorite) getTripById(id = id)
            }
        }
    }
}