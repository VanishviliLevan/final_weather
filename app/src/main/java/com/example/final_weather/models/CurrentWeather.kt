package com.example.final_weather.models

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("temp_c")
    val currentC:Float

)
