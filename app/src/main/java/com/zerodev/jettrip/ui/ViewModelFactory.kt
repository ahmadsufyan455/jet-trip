package com.zerodev.jettrip.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zerodev.jettrip.repository.TripRepository
import com.zerodev.jettrip.ui.screen.detail.DetailScreenViewModel
import com.zerodev.jettrip.ui.screen.favorite.FavoriteScreenViewModel
import com.zerodev.jettrip.ui.screen.home.HomeScreenViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val tripRepository: TripRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(tripRepository) as T
        } else if (modelClass.isAssignableFrom(DetailScreenViewModel::class.java)) {
            return DetailScreenViewModel(tripRepository) as T
        } else if (modelClass.isAssignableFrom(FavoriteScreenViewModel::class.java)) {
            return FavoriteScreenViewModel(tripRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}