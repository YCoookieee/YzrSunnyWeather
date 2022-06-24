package com.example.yzrsunnyweather.logic.model

import com.google.gson.annotations.SerializedName

data class DailyWeatherResponse(val status: String, val result: Result) {
    data class Result(val daily: Daily)

    data class Daily(
        @SerializedName("temperature") val temperatureList: List<Temperature>,
        @SerializedName("skycon") val skyConList: List<SkyCon>,
        @SerializedName("life_index") val lifeIndex: LifeIndex
    )

    data class Temperature(val max: Float, val min: Float)

    data class SkyCon(val value: String, val date: String)

    data class LifeIndex(
        val coldRisk: List<LifeDescription>,
        val carWashing: List<LifeDescription>,
        val ultraviolet: List<LifeDescription>,
        val dressing: List<LifeDescription>
    )

    data class LifeDescription(val desc: String)
}
