package com.example.yzrsunnyweather.logic

import android.content.Context
import androidx.lifecycle.liveData
import com.example.yzrsunnyweather.logic.dao.PlaceDao
import com.example.yzrsunnyweather.logic.model.PlaceResponse
import com.example.yzrsunnyweather.logic.model.Weather
import com.example.yzrsunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {

    fun savePlace(place: PlaceResponse.Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealTimeWeather(lng, lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtimeWeatherResponse = deferredRealtime.await()
            val dailyWeatherResponse = deferredDaily.await()
            if (realtimeWeatherResponse.status == "ok" && dailyWeatherResponse.status == "ok") {
                val weather = Weather(
                    realtimeWeatherResponse.result.realtime,
                    dailyWeatherResponse.result.daily
                )
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime weather status is ${realtimeWeatherResponse.status}" +
                                "daily weather status is ${dailyWeatherResponse.status}"
                    )
                )
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}