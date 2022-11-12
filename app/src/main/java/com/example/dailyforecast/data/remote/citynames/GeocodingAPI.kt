package com.example.dailyforecast.data.remote.citynames

import com.example.dailyforecast.model.bodyapi.ForecastBody
import com.example.dailyforecast.model.bodyapi.GeocodingBody
import com.example.dailyforecast.model.cityInfoData.CityModel
import com.example.dailyforecast.model.wtherModel.WitherDataRoot
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingAPI {
    @GET("/geo/1.0/direct")
    suspend fun getcitydata(
        @Query("q") citeyName:String,@Query("appid") appid:String
    ): List<CityModel>
}