package com.example.wheaterapiconsummer.service

import com.example.wheaterapiconsummer.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    suspend fun getWeather(@Query(value="key") key: String, @Query(value = "q") query: String, @Query(value="aqi") aqi: String): WeatherData
}
