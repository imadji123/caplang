package com.imadji.mobile.caplang

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import com.lokalise.sdk.LokaliseContextWrapper

abstract class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            // Inject the Lokalise SDK into the activity context
            super.attachBaseContext(LokaliseContextWrapper.wrap(it))
        } ?: super.attachBaseContext(newBase)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        AppUtils.updateLocale(MainApplication.appContext())
    }
}