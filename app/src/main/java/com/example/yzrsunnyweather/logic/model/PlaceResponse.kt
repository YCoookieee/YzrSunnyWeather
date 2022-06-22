package com.example.yzrsunnyweather.logic.model

import com.google.gson.annotations.SerializedName


data class PlaceResponse(val status: String, val query: String, val places: List<Place>)

data class Place(
    val id: String,
    val name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String,
    @SerializedName("place_id") val placeId: String
)

data class Location(val lng: String, val lat: String)
