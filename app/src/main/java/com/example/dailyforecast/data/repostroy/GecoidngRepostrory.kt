package com.example.dailyforecast.data.repostroy

import android.util.Log
import com.example.dailyforecast.data.remote.citynames.GeocodingAPI
import com.example.dailyforecast.model.bodyapi.GeocodingBody
import com.example.dailyforecast.model.cityInfoData.CityModel
import com.example.dailyforecast.util.Resosre
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


class GecoidngRepostrory @Inject constructor(
 private val gecoingApi: GeocodingAPI
){
    suspend  fun getcitydata(
        grcodingBody: GeocodingBody
    ): Resosre<List<CityModel>> {

        val response = try {

            gecoingApi.getcitydata(citeyName = grcodingBody.citeyName,grcodingBody.appid)
        } catch (e: Exception) {
            Log.d("Abous", "getcitydata: Error ${e.message}")
            return Resosre.error("Error oucerd ${e.message}")
        }
        return Resosre.succses(response)

    }
}