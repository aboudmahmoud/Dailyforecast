package com.example.dailyforecast.model.wtherModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    @SerialName("3h")
    val _3h: Double?
)