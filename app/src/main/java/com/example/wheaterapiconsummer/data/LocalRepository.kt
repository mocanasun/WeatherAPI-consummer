package com.example.wheaterapiconsummer.data

import android.content.Context
import com.example.wheaterapiconsummer.model.WeatherData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface LocalRepository {
    suspend fun getWeatherData(query: String = "initial"): WeatherData
    suspend fun saveWeatherData(data: WeatherData)
}
class LocalRepositoryImpl(@ApplicationContext context : Context): LocalRepository {

    private val prefs = context.getSharedPreferences("weather", Context.MODE_PRIVATE)
    private val DATA_KEY = "data_key"
    override suspend fun getWeatherData(query: String): WeatherData =
        prefs.getString(DATA_KEY, null).let {
            if (it != null) {
                Json.decodeFromString<WeatherData>(it)
            }
            else {
                //initial state
                WeatherData()
            }
        }

    override suspend fun saveWeatherData(data: WeatherData): Unit = prefs.edit().putString(DATA_KEY, Json.encodeToString(data)).apply()

}
