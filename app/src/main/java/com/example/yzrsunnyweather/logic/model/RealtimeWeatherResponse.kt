package com.example.yzrsunnyweather.logic.model

import com.google.gson.annotations.SerializedName

data class RealtimeWeatherResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)

    data class Realtime(
        val temperature: Float,
        @SerializedName("skycon") val skyCon: String,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}
