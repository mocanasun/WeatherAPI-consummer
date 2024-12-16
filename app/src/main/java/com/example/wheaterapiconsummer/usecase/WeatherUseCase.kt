package com.example.wheaterapiconsummer.usecase

import com.example.wheaterapiconsummer.data.LocalRepository
import com.example.wheaterapiconsummer.data.WeatherApiRepository
import com.example.wheaterapiconsummer.model.WeatherData
import com.example.wheaterapiconsummer.service.ResultWrapper

class WeatherUseCase(private val remote: WeatherApiRepository, private val localRepository: LocalRepository) {

    suspend fun fetchWeatherData(query: String): ResultWrapper<WeatherData> = remote.getWeatherData(query).also {
        when (it) {
            is ResultWrapper.Success -> {
                localRepository.saveWeatherData(it.value)
            }
            else -> {}
        }

    }

    suspend fun getInitialState(): WeatherData = localRepository.getWeatherData()
}
