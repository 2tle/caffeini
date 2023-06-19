package io.twotle.uimakercompose.util.sharedpreferences

import android.app.Application

class SharedPreferences : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}