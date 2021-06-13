package com.imadji.mobile.caplang

import android.app.Application
import android.content.Context
import com.yariksoffice.lingver.Lingver
import android.content.res.Configuration
import android.util.Log
import com.lokalise.sdk.Lokalise
import com.lokalise.sdk.LokaliseCallback
import com.lokalise.sdk.LokaliseUpdateError

class MainApplication : Application() {

    private var lokaliseCallback: LokaliseCallback? = null

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

        if (BuildConfig.DEBUG) {
            Lokalise.clearAllCallbacks()

            lokaliseCallback = object : LokaliseCallback {
                override fun onUpdateFailed(error: LokaliseUpdateError) {
                    Log.d("Lokalise SDK", "Lokalise onUpdateFailed ${error.name}")
                }

                override fun onUpdateNotNeeded() {
                    Log.d("Lokalise SDK", "Lokalise onUpdateNotNeeded")
                }

                override fun onUpdated(oldBundleId: Long, newBundleId: Long) {
                    Log.d("Lokalise SDK", "Lokalise onUpdated from $oldBundleId to $newBundleId")
                }
            }

            lokaliseCallback?.let { Lokalise.addCallback(it) }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        AppUtils.updateLocale(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        lokaliseCallback?.let {
            Lokalise.removeCallback(it)
        }
    }

    companion object {

        lateinit var instance: MainApplication

        fun appContext(): Context {
            return instance.applicationContext
        }

    }
}