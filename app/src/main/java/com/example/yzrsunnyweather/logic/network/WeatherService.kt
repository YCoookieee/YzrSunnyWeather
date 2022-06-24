package com.example.yzrsunnyweather.logic.network

import com.example.yzrsunnyweather.SunnyWeatherApplication
import com.example.yzrsunnyweather.logic.model.DailyWeatherResponse
import com.example.yzrsunnyweather.logic.model.RealtimeWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeWeatherResponse>

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<DailyWeatherResponse>
}