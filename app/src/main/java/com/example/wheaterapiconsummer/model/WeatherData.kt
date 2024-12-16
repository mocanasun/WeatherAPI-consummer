package com.example.wheaterapiconsummer.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
  @SerializedName("location" ) var location : Location? = Location(),
  @SerializedName("current"  ) var current  : Current?  = Current()
)
