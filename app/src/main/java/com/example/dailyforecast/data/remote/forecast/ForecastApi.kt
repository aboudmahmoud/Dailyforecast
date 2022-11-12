package com.example.dailyforecast.data.remote.forecast

import com.example.dailyforecast.model.bodyapi.ForecastBody
import com.example.dailyforecast.model.wtherModel.WitherDataRoot
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
    @GET("/data/2.5/forecast")
    suspend fun getForescateData(
        @Query("lat") lat:String,@Query("lon") lon:String ,
        @Query("appid") appid:String
    ): WitherDataRoot
}