package com.example.yeplapp.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Response(val businesses: List<Businesses>)

@Parcelize
data class Businesses(
    val id: String = "",
    val alias: String = "",
    val name: String = "",
    val image_url: String = "",
    val is_closed: Boolean = false,
    val review_count: Int = 0,
    val categories: List<Categories> = emptyList(),
    val rating: Double = 0.0,
    val coordinates: Coordinates = Coordinates(0.0,0.0),
    val price: String = "",
    val location: Location = Location(emptyList()),
    val display_phone: String = ""
) : Parcelable

@Parcelize
data class Categories(
    val alias: String,
    val title: String
): Parcelable

@Parcelize
data class Coordinates(
    val latitude: Double,
    val longitude: Double
):Parcelable

@Parcelize
data class Location(val display_address: List<String>): Parcelable

