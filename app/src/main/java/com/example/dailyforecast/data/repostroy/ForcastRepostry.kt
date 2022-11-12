package com.example.dailyforecast.data.repostroy


import android.util.Log
import com.example.dailyforecast.data.remote.forecast.ForecastApi
import com.example.dailyforecast.model.bodyapi.ForecastBody
import com.example.dailyforecast.model.cityInfoData.CityModel
import com.example.dailyforecast.model.wtherModel.WitherDataRoot
import com.example.dailyforecast.util.Resosre
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.http.Body
import javax.inject.Inject


class ForcastRepostry  @Inject constructor(
    private val forcastApi: ForecastApi
) {
    suspend fun getForescateData(
      forecatBody: ForecastBody
    ): Resosre<WitherDataRoot> {
        val response = try {
            Log.d("Abous", "getForescateData:suc ")
            forcastApi.getForescateData(lat=forecatBody.lat,lon =forecatBody.lon, appid =forecatBody.appid )
        } catch (e: Exception) {
            Log.d("Abous", "getForescateData:error ${e.message}")
            return Resosre.error("Error oucerd ${e.message}")
        }
        return Resosre.succses(response)
    }
}