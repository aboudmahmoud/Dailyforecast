package com.example.dailyforecast.di

import com.example.dailyforecast.data.remote.citynames.GeocodingAPI
import com.example.dailyforecast.data.remote.forecast.ForecastApi
import com.example.dailyforecast.util.Constans.BASE_URL
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.ignoreIoExceptions
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppMouldle {
    @Provides
    @Singleton
    fun proivideOkHttpcline(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor { chain ->
                val builder: Request.Builder = chain.request().newBuilder()

                //builder.addHeader("Content-Type", "multipart/form-data")
                val request: Request = builder.build()
                val response: okhttp3.Response = chain.proceed(request)
                response
            }
            .followRedirects(false).build()
    }


    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideRetfit(): Retrofit {
        val connectionType= "application/json".toMediaType()
        //We didnt Recive AllData So We Igonire it
        val json = Json {
            ignoreUnknownKeys = true

        }
        return Retrofit.Builder().client(
            proivideOkHttpcline()
        ).baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(connectionType)).addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create())).build()
    }

    @Singleton
    @Provides
    fun provideGeocodingAPI(retrofit: Retrofit): GeocodingAPI {
        return retrofit.create(GeocodingAPI::class.java)
    }
    @Singleton
    @Provides
    fun provideForecastApiAPI(retrofit: Retrofit): ForecastApi {
        return retrofit.create(ForecastApi::class.java)
    }

}