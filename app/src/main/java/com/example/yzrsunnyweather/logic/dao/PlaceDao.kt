package com.example.yzrsunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.yzrsunnyweather.SunnyWeatherApplication
import com.example.yzrsunnyweather.logic.model.PlaceResponse
import com.google.gson.Gson

object PlaceDao {

    fun savePlace(place: PlaceResponse.Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): PlaceResponse.Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, PlaceResponse.Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

}