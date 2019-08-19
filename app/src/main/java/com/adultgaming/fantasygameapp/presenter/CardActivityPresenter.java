package com.adultgaming.fantasygameapp.presenter;

import com.adultgaming.fantasygameapp.data.CardActivityInteractor;
import com.adultgaming.fantasygameapp.model.CardEvent;
import com.adultgaming.fantasygameapp.model.GamesEvent;
import com.adultgaming.fantasygameapp.view.CardActivityInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CardActivityPresenter {
    CardActivityInteractor mCardInteractor;
    CardActivityInterface mCardView;

    public CardActivityPresenter(CardActivityInteractor mCardInteractor, CardActivityInterface mCardView) {
        this.mCardInteractor = mCardInteractor;
        this.mCardView = mCardView;
    }

    public void getCardsList(String language, String gameTitle) {
        mCardInteractor.getCardsList(language, gameTitle);
    }

    /**
     * Event of inflating games list
     */
    @Subscribe
    public void inflateCardList(CardEvent event) {
        mCardView.showCardsList(event.getCardList());
    }

    public void registerSubscribers() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void unregisterSubscribers() {
        EventBus.getDefault().unregister(this);
    }

    public void getColorCardsList(String language, String gameTitle, String color) {
        mCardInteractor.getColorCardsList(language, gameTitle, color);
    }
}
