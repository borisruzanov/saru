package com.adultgaming.fantasygameapp.data;

import com.adultgaming.fantasygameapp.utils.FirebaseManager;

public class CardActivityInteractor {
    public void getCardsList(String language, String gameTitle) {
        FirebaseManager.getInstance().getCardList(language, gameTitle);
    }

    public void getColorCardsList(String language, String gameTitle, String color) {
        FirebaseManager.getInstance().getColorCardList(language, gameTitle, color);

    }
}
