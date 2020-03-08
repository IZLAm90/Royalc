package com.example.royal.Model;

public class OfferModel {
    private String name;
    private String image;
    private String date;
    private String Desc;

    public OfferModel(String name, String image, String date, String desc) {
        this.name = name;
        this.image = image;
        this.date = date;
        Desc = desc;
    }

    public OfferModel() {
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return Desc;
    }

}
