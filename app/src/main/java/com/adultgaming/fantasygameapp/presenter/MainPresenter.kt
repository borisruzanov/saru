package com.adultgaming.fantasygameapp.presenter

import com.adultgaming.fantasygameapp.common.AdLauncher
import com.adultgaming.fantasygameapp.data.MainInteractor
import com.adultgaming.fantasygameapp.model.GamesEvent
import com.adultgaming.fantasygameapp.utils.SharPrefManager
import com.adultgaming.fantasygameapp.view.MainInterface
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainPresenter(var mMainView: MainInterface?, val mMainInteractor: MainInteractor) {

    /**
     * Checking if user have language otherwise show list of games
     */
    fun checkingLanguage() {
//         mMainView?.callLanguageDialog()
        if (mMainInteractor.getUserLanguage() == "") {
//            getGameList("ru")
            mMainView?.callLanguageDialog()
        }
//        else EventBus.getDefault().post(LanguageEvent(mMainInteractor.getUserLanguage()))
    }

    /**
     * Event of inflating games list
     */
    @Subscribe
    fun inflateGamesList(event: GamesEvent) {
        mMainView?.showGamesList(event.gameList)
    }

    fun registerSubscribers() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    fun unregisterSubscribers() {
        EventBus.getDefault().unregister(this)
    }

    fun intentToNextActivity(type: String, title: String) {
        if (type == "regular") mMainView?.sendToColorActivity(title) else mMainView?.sendToCardsActivity(title)
    }

    fun getGameList(lang: String) {
        mMainInteractor.getGamesList(lang)
    }

    fun launchAd() {
        if (mMainInteractor.getUserLanguage() != "") AdLauncher.getInstance().launchAd()
    }

    fun grabAdTimeLeft(): String? {
        return SharPrefManager.getInstance().grabAdTimeLeft()
    }

}