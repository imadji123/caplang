package com.imadji.mobile.caplang

import android.app.Application
import android.content.Context
import com.yariksoffice.lingver.Lingver

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        setupLingver()

    }

    // https://github.com/YarikSOffice/lingver
    private fun setupLingver() {
        val appLanguage = AppUtils.getCurrentAppLanguage(this)
        Lingver.init(this, appLanguage)
    }

    companion object {

        lateinit var instance: MainApplication

        fun appContext(): Context {
            return instance.applicationContext
        }

    }
}