package com.example.wheaterapiconsummer.service

import com.example.wheaterapiconsummer.model.ErrorResponse
import com.example.wheaterapiconsummer.model.WeatherData

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null): ResultWrapper<Nothing>()
    data object NetworkError: ResultWrapper<Nothing>()
}