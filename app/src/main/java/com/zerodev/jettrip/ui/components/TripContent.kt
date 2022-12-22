package com.zerodev.jettrip.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.zerodev.jettrip.model.Trip

@Composable
fun TripContent(
    trips: List<Trip>,
    emptyMessage: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    if (trips.isNotEmpty()) {
        LazyColumn(
            modifier = modifier.testTag("tripList"),
        ) {
            items(trips, key = { it.id }) { trip ->
                TripItem(
                    imageUrl = trip.image,
                    name = trip.name,
                    location = trip.location,
                    shortDesc = trip.shortDesc,
                    modifier = modifier.clickable {
                        navigateToDetail(trip.id)
                    }
                )
            }
        }
    } else {
        EmptyPage(emptyMessage = emptyMessage)
    }
}