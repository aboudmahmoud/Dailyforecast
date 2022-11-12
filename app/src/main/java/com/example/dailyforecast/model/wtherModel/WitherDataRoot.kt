package com.example.dailyforecast.model.wtherModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WitherDataRoot(
    val cod:String?,
    val message:Int?,
    val cnt:Int?,
    @SerialName("list")
    val listOfWithers:List<ListOFWithers>?,
    val city:City?,
)
