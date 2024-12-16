package com.example.wheaterapiconsummer.data

import com.example.wheaterapiconsummer.model.ErrorResponse
import com.example.wheaterapiconsummer.model.WeatherData
import com.example.wheaterapiconsummer.service.ResultWrapper
import com.example.wheaterapiconsummer.service.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okio.IOException
import retrofit2.HttpException


// Repository interface
interface WeatherApiRepository {
    suspend fun getWeatherData(query: String): ResultWrapper<WeatherData>
}
class WeatherApiRepositoryImpl: WeatherApiRepository {
    private val weatherService = RetrofitInstance.weatherService

    override suspend fun getWeatherData(query: String): ResultWrapper<WeatherData> {
        return handleApiCall { weatherService.getWeather(apiKey, query, aqi)}
    }

    companion object {
        private const val apiKey = "28b562ee4daf497bb39133222241512"
        private const val aqi = "no"
    }
}


suspend fun <T> handleApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
    return withContext(Dispatchers.IO) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.charStream()?.readText()?.let {
            Json.decodeFromString<ErrorResponse>(it)
        }
    } catch (exception: Exception) {
        null
    }
}
