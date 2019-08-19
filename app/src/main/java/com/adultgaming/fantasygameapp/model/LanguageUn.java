package com.adultgaming.fantasygameapp.model;

public class LanguageUn {

    String title;
    String image;
    String shortLang;

    public LanguageUn() {
    }

    public LanguageUn(String title, String image, String shortLang) {
        this.title = title;
        this.image = image;
        this.shortLang = shortLang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShortLang() {
        return shortLang;
    }

    public void setShortLang(String shortLang) {
        this.shortLang = shortLang;
    }
}

