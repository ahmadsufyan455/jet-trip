package com.zerodev.jettrip.repository

import com.zerodev.jettrip.data.TripDataSource
import com.zerodev.jettrip.model.Trip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class TripRepository {

    private val listTrip = mutableListOf<Trip>()

    init {
        if (listTrip.isEmpty()) {
            listTrip.addAll(TripDataSource.trips)
        }
    }

    fun getTrips(): Flow<List<Trip>> {
        return flowOf(listTrip)
    }

    fun searchDestination(query: String): Flow<List<Trip>> {
        return flowOf(
            listTrip.filter { trip ->
                trip.name.contains(query, ignoreCase = true)
            }
        )
    }

    fun getTripById(id: Long): Trip {
        return listTrip.first {
            it.id == id
        }
    }

    fun addToFavorite(id: Long, isFavorite: Boolean): Flow<Boolean> {
        val index = listTrip.indexOfFirst { it.id == id }
        val result = if (index >= 0) {
            val trip = listTrip[index]
            listTrip[index] = trip.copy(isFavorite = isFavorite)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getFavoriteTrip(): Flow<List<Trip>> {
        return getTrips().map { trips ->
            trips.filter { trip ->
                trip.isFavorite
            }
        }
    }

    companion object {
        @Volatile
        private var instance: TripRepository? = null

        fun getInstance(): TripRepository =
            instance ?: synchronized(this) {
                TripRepository().apply {
                    instance = this
                }
            }
    }
}