package com.example.dailyforecast.util

import java.text.ParseException
import java.text.SimpleDateFormat

object Constans {
    const val BASE_URL ="https://api.openweathermap.org"
    const val IsText=false
    const val isSerach=true
    fun getAbbreviatedFromDateTime(dateTime: String, dateFormat: String, field: String): String? {
        val input = SimpleDateFormat(dateFormat)
        val output = SimpleDateFormat(field)
        try {
            val getAbbreviate = input.parse(dateTime)    // parse input
            return output.format(getAbbreviate)    // format output
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }

    fun getFromDateTime(dateTime: String, dateFormat: String): String? {
        val input = SimpleDateFormat(dateFormat)

        try {
            val getAbbreviate = input.parse(dateTime)    // parse input
            return input.format(getAbbreviate)    // format output
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }
}