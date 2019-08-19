package com.adultgaming.fantasygameapp.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.adultgaming.fantasygameapp.MyApp

class SharedPrefManager {

    private val PREF_NAME = "preferences"
    private val LANGUAGE = "language"

    /**
     * Set user language
     */
    fun setUserLanguageShPref(language: String) {
        getSharedPreferencesEditor().putString(LANGUAGE, language)
        getSharedPreferencesEditor().apply()
    }

    /**
     * Get user language
     */
    fun getUserLanguageShPref(): String? {
        return getSharPreferences(PREF_NAME, MODE_PRIVATE).getString(LANGUAGE, null)
    }

    /**
     * Return instance of Shared preferences
     * @param prefName
     * @param modePrivate
     */
    private fun getSharPreferences(prefName: String, modePrivate: Int): SharedPreferences {
        return MyApp.getAppContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    }

    /**
     * Return Shared preferences editor
     */
    private fun getSharedPreferencesEditor(): SharedPreferences.Editor {
        return getSharPreferences(PREF_NAME, MODE_PRIVATE).edit()
    }
}