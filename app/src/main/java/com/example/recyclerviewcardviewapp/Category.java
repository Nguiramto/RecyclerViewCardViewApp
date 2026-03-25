package com.example.recyclerviewcardviewapp;

import java.util.List;

import com.example.recyclerviewcardviewapp.CardItem;

public class Category {

    private String nameCategory;
    private List<CardItem> items;

    public Category(String nameCategory, List<CardItem> items) {
        this.nameCategory = nameCategory;
        this.items = items;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public List<CardItem> getItems() {
        return items;
    }
}