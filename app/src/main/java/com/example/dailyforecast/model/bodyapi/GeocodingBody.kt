package com.example.dailyforecast.model.bodyapi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class GeocodingBody(

    val citeyName:String
):RootHeader()
