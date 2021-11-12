package com.example.newcoinstatsapp.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

object AsyncUtils {
    val executor by lazy {
        Executors.newSingleThreadExecutor()
    }

    val handler by lazy {
        Handler(Looper.getMainLooper())
    }
}