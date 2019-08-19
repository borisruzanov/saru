package com.adultgaming.fantasygameapp.data

import com.adultgaming.fantasygameapp.utils.FirebaseManager
import com.adultgaming.fantasygameapp.utils.SharPrefManager

class MainInteractor {

    /**
     * Getting language value from Shared Preferences
     */
    fun getUserLanguage(): String {
        return SharPrefManager.getInstance().grabUserLanguageShPref()
    }

    fun getGamesList(lang: String) {
        FirebaseManager.getInstance().getGamesList(lang)
    }

}