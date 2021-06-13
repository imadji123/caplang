package com.imadji.mobile.caplang

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.core.content.edit
import com.lokalise.sdk.Lokalise
import com.yariksoffice.lingver.Lingver
import java.util.*

object AppUtils {

    fun setAppLanguage(context: Context, langCode: String) {
        val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        preferences.edit(commit = true) { putString(KEY_APP_LANGUAGE, langCode) }
    }

    fun getCurrentAppLanguage(context: Context): String {
        val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return preferences.getString(KEY_APP_LANGUAGE, DEFAULT_APP_LANGUAGE) ?: DEFAULT_APP_LANGUAGE
    }

    fun updateLocale(context: Context) {
        val appLanguage = getCurrentAppLanguage(context)
        updateLocale(context = context, language = appLanguage)
    }

    fun updateLocale(context: Context, language: String) {
        setAppLanguage(context, language)
        Lingver.getInstance().setLocale(context, language)
        Lokalise.setLocale(context = context, languageISO = language)
    }

    fun updatedLocaleContext(context: Context, language: String): ContextWrapper {
        val locale = Locale.forLanguageTag(language)
        val configuration: Configuration = context.resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(locale)
            LocaleList.setDefault(localeList)
            configuration.setLocales(localeList)
        } else {
            configuration.locale = locale
        }
        val finalContext = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            context.createConfigurationContext(configuration)
        } else {
            context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
            context
        }

        return ContextWrapper(finalContext)
    }
}