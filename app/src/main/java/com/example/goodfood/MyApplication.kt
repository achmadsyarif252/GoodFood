package com.example.goodfood

import android.app.Application
import com.example.goodfood.di.Injection

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Injection.init(this)
    }
}