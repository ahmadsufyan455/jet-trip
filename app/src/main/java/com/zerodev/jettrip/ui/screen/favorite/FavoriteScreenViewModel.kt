package com.zerodev.jettrip.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.jettrip.model.Trip
import com.zerodev.jettrip.repository.TripRepository
import com.zerodev.jettrip.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteScreenViewModel(private val tripRepository: TripRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Trip>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Trip>>> get() = _uiState

    fun getFavoriteTrip() {
        viewModelScope.launch {
            tripRepository.getFavoriteTrip()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { trips ->
                    _uiState.value = UiState.Success(trips)
                }
        }
    }
}