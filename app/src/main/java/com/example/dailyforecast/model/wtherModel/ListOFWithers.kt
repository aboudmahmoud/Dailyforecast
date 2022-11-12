package com.example.dailyforecast.model.wtherModel


import kotlinx.serialization.Serializable

@Serializable
data class ListOFWithers(
    val clouds: Clouds?,
    val dt: Int?,
    val dt_txt: String?,
    val main: Main?,
    val pop: Double?,
    val rain: Rain?=null,
    val sys: Sys?,
    val visibility: Int?,
    val weather: List<Weather>?,
    val wind: Wind?
)
