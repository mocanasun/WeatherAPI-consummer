package com.example.wheaterapiconsummer.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Condition (

  @SerializedName("text" ) var text : String? = null,
  @SerializedName("icon" ) var icon : String? = null,
  @SerializedName("code" ) var code : Int?    = null

)
