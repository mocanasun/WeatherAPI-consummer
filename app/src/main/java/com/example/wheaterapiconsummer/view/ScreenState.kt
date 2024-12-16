package com.example.wheaterapiconsummer.view

import com.example.wheaterapiconsummer.model.WeatherModel

data class ScreenState (
    val weatherModel: WeatherModel?,
    val viewState: ViewState
) {
    companion object {
        val initValue = ScreenState(null, ViewState.NO_SELECTION)
    }
}
