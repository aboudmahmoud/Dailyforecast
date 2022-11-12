package com.example.dailyforecast.model.bodyapi

import com.example.dailyforecast.BuildConfig
import kotlinx.serialization.Serializable


open class RootHeader(
    val appid:String= BuildConfig.API_KEY
)
