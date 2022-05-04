package com.example.yeplapp.data.model

data class Response(val businesses: List<Businesses>)

data class Businesses(
    val id: String,
    val alias: String,
    val image_url: String,
    val is_closed: Boolean,
    val review_count: Int,
    val categories: List<Categories>,
    val rating: Double,
    val coordinates: Coordinates,
    val price: String,
    val location: Location,
    val display_phone: String
)

data class Categories(
    val alias: String,
    val title: String
)

data class Coordinates(
    val latitude: Double,
    val longitude: Double
)

data class Location(val display_address: List<String>)

