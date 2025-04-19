package com.example.laratonaskincare;

public class Category {

    private String name;
    private int imageCatId;

    public Category(String name, int imageCatId) {
        this.name = name;
        this.imageCatId = imageCatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageCatId() {
        return imageCatId;
    }

    public void setImageCatId(int imageCatId) {
        this.imageCatId = imageCatId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", imageResId=" + imageCatId +
                '}';
    }
}

