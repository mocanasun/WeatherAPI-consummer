package com.example.wheaterapiconsummer.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse (
    @SerializedName("error" ) var error : Error? = null,
)

@Serializable
data class Error (
    @SerializedName("code" ) var code : Int? = null,
    @SerializedName("message" ) var message : String? = null
)
