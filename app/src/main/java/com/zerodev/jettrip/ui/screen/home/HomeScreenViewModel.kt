package com.zerodev.jettrip.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.jettrip.model.Trip
import com.zerodev.jettrip.repository.TripRepository
import com.zerodev.jettrip.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val tripRepository: TripRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Trip>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Trip>>> get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getTrips() {
        viewModelScope.launch {
            tripRepository.getTrips()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { trips ->
                    _uiState.value = UiState.Success(trips)
                }
        }
    }

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            tripRepository.searchDestination(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { trips ->
                    _uiState.value = UiState.Success(trips)
                }
        }
    }
}