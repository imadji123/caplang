package com.imadji.mobile.caplang

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_english -> {
                changeLanguage("en")
                true
            }
            R.id.action_korean -> {
                changeLanguage("ko")
                true
            }
            R.id.action_japanese -> {
                changeLanguage("ja")
                true
            }
            R.id.action_indonesian -> {
                changeLanguage("in")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun changeLanguage(language: String) {
//        AppUtils.setAppLanguage(this, language)
        Lingver.getInstance().setLocale(MainApplication.appContext(), language)
        reloadApp()
    }

    private fun reloadApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}