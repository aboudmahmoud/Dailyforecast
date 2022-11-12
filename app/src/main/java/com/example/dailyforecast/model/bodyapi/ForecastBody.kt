package com.example.dailyforecast.model.bodyapi

import kotlinx.serialization.Serializable


data class ForecastBody(val lat:String,val lon:String): RootHeader()
