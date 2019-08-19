package com.adultgaming.fantasygameapp.model;

import java.util.List;

public class GamesEvent {
    List<Game> gameList;

    public GamesEvent(List<Game> gameList) {
        this.gameList = gameList;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
