package com.imadji.mobile.caplang

import android.app.Application
import android.content.Context

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        lateinit var instance: MainApplication

        fun appContext(): Context {
            return instance.applicationContext
        }

    }
}