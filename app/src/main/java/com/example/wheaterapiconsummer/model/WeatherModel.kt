package com.example.wheaterapiconsummer.model

/*
* - Free tier includes current weather data with:
    - **Temperature**.
    - **Weather condition** (including an icon URL).
    - **Humidity (%).**
    - **UV index.**
    - **Feels like** temperature.*/
data class WeatherModel(
    val location: String?,
    val tempCelsius: Double?,
    val tempFahrenheit: Double?,
    val condition: Condition?,
    val humidity: Double?,
    val uvIndex: Double?,
    val feelsLikeC: Double?,
    val feelsLikeF: Double?,
) {
    class Builder {
        private lateinit var weatherData: WeatherData
        fun data(weatherData: WeatherData): Builder {
            this.weatherData = weatherData
            return this
        }

        fun build() = with(weatherData) {
            WeatherModel(
                location = location?.name,
                tempCelsius = current?.tempC,
                tempFahrenheit = current?.tempF,
                condition = current?.condition,
                humidity = current?.humidity,
                uvIndex = current?.uv,
                feelsLikeC = current?.feelslikeC,
                feelsLikeF = current?.feelslikeF
            )
        }
    }
}
