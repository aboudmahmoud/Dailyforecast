package com.example.dailyforecast.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailyforecast.util.*


import com.example.dailyforecast.util.Constans.IsText


import com.example.dailyforecast.util.Constans.isSerach


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    // Here Is We check is TopBar or Serach
    var IsSerahORText by remember {
        mutableStateOf(IsText)
    }
    val SerachValue = remember {
        mutableStateOf("")
    }
    //Here We save Adders That we Serach For
    var TobBarTextBage: String by remember {
        mutableStateOf("Home Page")
    }


    Box(modifier = Modifier.fillMaxSize()) {
        //I use this or homeScreenViewModel::SerachCity
        val searchCity = remember<(String) -> Unit> {
            {
                IsSerahORText = !IsSerahORText
                homeScreenViewModel.SerachCity(it)
            }
        }
        Scaffold(

            topBar = {
                if (IsSerahORText == IsText) {
                    TopBBar(text = TobBarTextBage, {
                        IsSerahORText = !IsSerahORText
                    })
                } else if (IsSerahORText == isSerach) {
                    CoustemTextFielsSerah(
                        text = SerachValue.value,
                        onTextChange = {
                            SerachValue.value = it
                        }, onCloseClicke = {
                            IsSerahORText = !IsSerahORText
                        },
                        // onSerachCliced = homeScreenViewModel::SerachCity
                        onSerachCliced = searchCity
                    )
                }
            },
        ) {
            /* infoCity  I used the API called (Geocoding API)
            https://openweathermap.org/api/geocoding-api
            To Serach City Becuse The weather forecast Api Only take lat and lon  */
            when (val infoCity = homeScreenViewModel.CityDataInfoState.collectAsState().value) {
                is Resosre.error -> {
                    CoustemDiloage(true, infoCity.Messeg!!)
                }
                is Resosre.Loading -> {
                    LoadingBrogessFullWidth()
                }
                is Resosre.succses -> {
                    if (infoCity.data?.get(0)?.state != null) {
                        TobBarTextBage = infoCity.data?.get(0)?.state!!
                    } else {
                        TobBarTextBage = infoCity.data?.get(0)?.name!!
                    }
                }
                is Resosre.idel -> {}
            }
            /* infoWither  I used the API weather forecast  to get Deatils   */
            when (val infoWither =
                homeScreenViewModel.CityDataWitherInfoState.collectAsState().value) {
                is Resosre.error -> { CoustemDiloage(true, infoWither.Messeg!!) }
                is Resosre.Loading -> { LoadingBrogessFullWidth() }
                is Resosre.succses -> {
                    val data = infoWither.data?.listOfWithers?.get(0)
                    BoxWithConstraints(
                        Modifier.padding(it),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        WitherInfo(
                            Temp = data?.main?.temp.toString(),
                            MainWitherState = data?.weather?.get(0)?.main!!,
                            detials = data.weather.get(0).description!!,
                            humidity = data.main?.humidity.toString(),
                            date = data.dt_txt!!
                        )
                    }
                }
                is Resosre.idel -> {}
            }

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBBar(
    text: String,
    onSeracClick: () -> Unit
) {

    TopAppBar(
        title = {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.primary
            )


        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        actions = {
            IconButton(onClick = onSeracClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
            }
        })
}






@Composable
fun WitherInfo(
    Temp: String,
    MainWitherState: String,
    detials: String,
    humidity: String,
    date: String,
) {
    Card(
        Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .height(300.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing,
                )
            )
            .background(Color.White),
        shape = shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WitherBoxImagesData(
                Temp = Temp,
                MainWitherState = MainWitherState,
                detials = detials,
                humidity = humidity
            )
            BottomDayDetials(date)

        }
    }
}



