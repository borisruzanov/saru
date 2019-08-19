package com.adultgaming.fantasygameapp.view

import com.adultgaming.fantasygameapp.model.Game

interface MainInterface {
    fun callLanguageDialog()
    fun showGamesList(language: MutableList<Game>)
    fun sendToColorActivity(title: String)
    fun sendToCardsActivity(title: String)
}
