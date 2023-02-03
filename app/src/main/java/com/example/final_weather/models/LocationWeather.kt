package com.example.final_weather.models

data class LocationWeather(
    val name:String,
    val region:String,
    val country: String,
    val condition:ConditionWeather
)
