package com.adultgaming.fantasygameapp.model;

public class Game {
    private String background;
    private String description;
    private String tag;
    private String title;
    private String type;

    public Game() {
    }

    public Game(String background, String description, String tag, String title, String type) {
        this.background = background;
        this.description = description;
        this.tag = tag;
        this.title = title;
        this.type = type;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
