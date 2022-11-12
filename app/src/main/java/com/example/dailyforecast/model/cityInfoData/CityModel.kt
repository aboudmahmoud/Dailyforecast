package com.example.dailyforecast.model.cityInfoData

import kotlinx.serialization.Serializable

@Serializable
data class CityModel(
    val name:String?,
    val lat:Double?,
    val lon:Double?,
    val country:String?,
    val state:String?=null,
)
