package com.example.dailyforecast

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplaction:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}