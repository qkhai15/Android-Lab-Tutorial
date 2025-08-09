package com.example.recyclerviewexample.models;

public class Product {
    private String name, description, imageUrl;
    private double price, rating;

    public Product(String name, String description, double price, double rating, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public String getImageUrl() { return imageUrl; }
}
