package com.example.royal.Model;

public class TModel {
    String tabel;
    int price;

    public TModel() {
    }

    public TModel(String  tabel, int price) {
        this.tabel = tabel;
        this.price = price;
    }

    public String getTabel() {
        return tabel;
    }

    public int getPrice() {
        return price;
    }
}
