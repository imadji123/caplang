package com.imadji.mobile.caplang

import android.app.Application
import android.content.Context
import com.yariksoffice.lingver.Lingver
import android.content.res.Configuration
import com.lokalise.sdk.Lokalise

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        setupLingver()
        setupLokalise()
    }

    // https://github.com/YarikSOffice/lingver
    private fun setupLingver() {
        val appLanguage = AppUtils.getCurrentAppLanguage(this)
        Lingver.init(this, appLanguage)
    }

    private fun setupLokalise() {
        Lokalise.init(
            this,
            "74f2a96482cfd9bebeaec3748054c715ce2fa8b6",
            "2825574560c4cf140fffa0.27471745"
        )

        Lokalise.isPreRelease = BuildConfig.DEBUG
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        AppUtils.updateLocale(this)
    }

    companion object {

        lateinit var instance: MainApplication

        fun appContext(): Context {
            return instance.applicationContext
        }

    }
}