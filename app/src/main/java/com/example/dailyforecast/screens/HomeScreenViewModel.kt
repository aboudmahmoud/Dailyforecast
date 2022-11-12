package com.example.dailyforecast.screens
import android.util.Log
import kotlinx.coroutines.channels.Channel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyforecast.data.repostroy.ForcastRepostry
import com.example.dailyforecast.data.repostroy.GecoidngRepostrory
import com.example.dailyforecast.model.bodyapi.ForecastBody
import com.example.dailyforecast.model.bodyapi.GeocodingBody
import com.example.dailyforecast.model.cityInfoData.CityModel
import com.example.dailyforecast.model.wtherModel.WitherDataRoot
import com.example.dailyforecast.util.Resosre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val forcastRepostry: ForcastRepostry,
    private val gecoidngRepostrory: GecoidngRepostrory
): ViewModel() {


    private val _viewState = MutableStateFlow<Resosre<List<CityModel>?>>(Resosre.idel())
    val CityDataInfoState: StateFlow<Resosre<List<CityModel>?>> get() = _viewState

    private val _CityDataWitherInfoState = MutableStateFlow<Resosre<WitherDataRoot?>>(Resosre.idel())
    val CityDataWitherInfoState: StateFlow<Resosre<WitherDataRoot?>> get() = _CityDataWitherInfoState


     fun SerachCity(cityName: String) {
         _viewState.value= Resosre.Loading()
       viewModelScope.launch {
            _viewState.value = try {
                Log.d("Abous", "SerachCity: Succes done")
                val res= gecoidngRepostrory.getcitydata(GeocodingBody(cityName))
                when (res){

                    is Resosre.error -> {
                        Resosre.error(res.Messeg!!)
                    }

                    is Resosre.succses -> {
                        getForescateData(res.data?.get(0)?.lat.toString(),res.data?.get(0)?.lon.toString())
                        Resosre.succses(res.data)

                    }
                    else -> {
                        Resosre.Loading()
                    }
                }

            } catch (e: Exception) {
                Log.d("Abous", "SerachCity: Error oucerd ${e.message}")
                Resosre.error("Error oucerd ${e.message}")

            }

        }

    }

 private fun getForescateData(lat:String,Lon:String){
        _CityDataWitherInfoState.value= Resosre.Loading()
        viewModelScope.launch {
            _CityDataWitherInfoState.value=try {
                val res= forcastRepostry.getForescateData(ForecastBody(lat=lat,lon=Lon))
                when (res){

                    is Resosre.error -> {
                        Resosre.error(res.Messeg!!)
                    }

                    is Resosre.succses -> {
                        Log.d("Abous", "getForescateData: Succes done")
                        Resosre.succses(res.data)
                    }
                    else -> {
                        Resosre.Loading()
                    }
                }
            }  catch (e: Exception) {
                Log.d("Abous", "getForescateData: Error oucerd ${e.message}")
                Resosre.error("Error oucerd ${e.message}")

            }
        }
    }
}