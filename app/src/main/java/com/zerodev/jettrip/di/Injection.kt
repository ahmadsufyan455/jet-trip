package com.zerodev.jettrip.di

import com.zerodev.jettrip.repository.TripRepository

object Injection {
    fun provideRepository(): TripRepository {
        return TripRepository.getInstance()
    }
}