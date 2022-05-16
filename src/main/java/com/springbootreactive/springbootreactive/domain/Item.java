package com.springbootreactive.springbootreactive.domain;

import org.springframework.data.annotation.Id;


public class Item {
    private @Id String id;
    private String name;
    private String description;
    private double price;

    private Item(){}

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Item(String name,String description,double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
