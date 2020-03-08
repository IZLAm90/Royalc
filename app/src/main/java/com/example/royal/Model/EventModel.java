package com.example.royal.Model;

public class EventModel {
    private String name;
    private String image;
    private String date;
    private String Desc;

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

    public EventModel() {
    }

    public String getName() {
        return name;
    }

    public String  getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return Desc;
    }

    public EventModel(String name, String image, String date, String desc) {
        this.name = name;
        this.image = image;
        this.date = date;
        Desc = desc;
    }
}
