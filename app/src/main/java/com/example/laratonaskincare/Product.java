package com.example.laratonaskincare;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int imageID;
    private String description;
    private double price;
    private String category;
    private String other;
    private int inventoryCount;
    private int quantity;


    public Product(String name, int imageID, String description, double price, String category, String other, int inventoryCount) {
        this.name = name;
        this.imageID = imageID;
        this.description = description;
        this.price = price;
        this.category = category;
        this.other = other;
        this.inventoryCount = inventoryCount;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product(String name, double price, int imageID) {
        this.name = name;
        this.price = price;
        this.imageID = imageID;

    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", imageID=" + imageID +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", other='" + other + '\'' +
                ", inventoryCount=" + inventoryCount +
                ", quantity=" + quantity +
                '}';
    }
}
