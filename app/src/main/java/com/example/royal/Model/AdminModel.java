package com.example.royal.Model;

public class AdminModel {
    private String name;
    private String email;

    public AdminModel() {
    }

    public String getName() {
        return name;
    }

    public AdminModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
