package com.example.final_weather.api

import com.example.final_weather.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("current.json")
    fun getWeather(
        @Query("key") key:String,
        @Query("q") q:String
                 ): Call<WeatherResponse>

}