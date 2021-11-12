package com.example.newcoinstatsapp

import android.app.Application
import com.example.newcoinstatsapp.repository.CoinRepository

class CoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CoinRepository(this)
    }
}