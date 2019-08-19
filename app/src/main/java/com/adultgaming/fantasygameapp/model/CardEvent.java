package com.adultgaming.fantasygameapp.model;

import java.util.List;

public class CardEvent {
    List<CardUnit> cardList;

    public CardEvent(List<CardUnit> cardList) {
        this.cardList = cardList;
    }

    public List<CardUnit> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardUnit> cardList) {
        this.cardList = cardList;
    }
}
