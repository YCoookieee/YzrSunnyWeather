package com.example.yzrsunnyweather.logic.model

data class Weather(val realtime: RealtimeWeatherResponse.Realtime, val daily: DailyWeatherResponse.Daily)
