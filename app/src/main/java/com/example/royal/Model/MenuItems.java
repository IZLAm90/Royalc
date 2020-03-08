package com.example.royal.Model;

public class MenuItems {
    private int image;
    private String name;
    private String price;
    private int love;
    public void changlove(int love){
        this.love=love;
    }
    public MenuItems() {
    }

    public int getImage() {
        return image;
    }

    public MenuItems(int image, String name, String price, int love) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.love = love;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getLove() {
        return love;
    }
}
