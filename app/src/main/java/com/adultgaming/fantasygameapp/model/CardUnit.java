package com.adultgaming.fantasygameapp.model;

public class CardUnit {
    String title;
    String description;
    String image;
    String game;

    public CardUnit() {
    }

    public CardUnit(String title, String description, String image, String game) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.game = game;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
