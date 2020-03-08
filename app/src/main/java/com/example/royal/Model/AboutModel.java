package com.example.royal.Model;

public class AboutModel {
    private String title;
    private String de;
    private int image;

    public AboutModel() {
    }

    public AboutModel(String title, String de, int image) {
        this.title = title;
        this.de = de;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDe() {
        return de;
    }

    public int getImage() {
        return image;
    }
}
