package com.example.goodfood

import android.app.Application
import com.example.goodfood.core.di.Injection
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Injection.init(this)
    }
}