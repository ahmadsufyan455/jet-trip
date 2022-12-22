package com.zerodev.jettrip.model

data class Trip(
    val id: Long,
    val image: String,
    val name: String,
    val location: String,
    val shortDesc: String,
    val longDesc: String,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    var isFavorite: Boolean = false
)
