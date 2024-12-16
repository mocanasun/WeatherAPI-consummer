package com.example.wheaterapiconsummer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheaterapiconsummer.model.ErrorResponse
import com.example.wheaterapiconsummer.model.WeatherData
import com.example.wheaterapiconsummer.model.WeatherModel
import com.example.wheaterapiconsummer.service.ResultWrapper
import com.example.wheaterapiconsummer.usecase.WeatherUseCase
import com.example.wheaterapiconsummer.view.MainViewEvent
import com.example.wheaterapiconsummer.view.OnClickFetch
import com.example.wheaterapiconsummer.view.ScreenState
import com.example.wheaterapiconsummer.view.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor( private val weatherUseCase: WeatherUseCase): ViewModel() {

    // State is maintained using StateFlow
    private val _state = MutableStateFlow(ScreenState.initValue)
    val state: StateFlow<ScreenState> = _state.asStateFlow()

    // Function to get the current state
    private fun currentState(): ScreenState = _state.value

    // Function to update the state
    private fun updateState(newState: ScreenState) {
        _state.value = newState
    }

    // Process events sent from the view
    fun onEvent(event: MainViewEvent) {
        viewModelScope.launch {
            when (event) {
                is OnClickFetch -> fetchWeatherData(event.query)
            }
        }
    }

    private fun fetchWeatherData(query: String) {
        viewModelScope.launch {
            if (query.isNullOrEmpty()) {
                val data = weatherUseCase.getInitialState()
                updateState(
                    ScreenState(
                        weatherModel = WeatherModel.Builder().data(data).build(),
                        if (data.location?.name.isNullOrEmpty() ) ViewState.NO_SELECTION else ViewState.SHOW_RESULT
                    )
                )
            } else {
                when (val response = weatherUseCase.fetchWeatherData(query)) {
                    is ResultWrapper.NetworkError -> showGenericError()
                    is ResultWrapper.GenericError -> showGenericError()
                    is ResultWrapper.Success -> showSuccess(response.value)
                }
            }
        }
    }

    private fun showSuccess(data: WeatherData) {
        updateState(
            ScreenState(
                weatherModel = WeatherModel.Builder().data(data).build(),
                viewState =  if (data.location?.name.isNullOrEmpty() ) ViewState.NO_RESULT else ViewState.SHOW_RESULT)
        )
    }

    private fun showGenericError(errorResponse: ErrorResponse? = null) {
        updateState(
            ScreenState(
                weatherModel = null,
                viewState =  ViewState.ERROR)
        )
    }
}
