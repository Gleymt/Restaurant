package com.restaurant;

import java.util.Date;

public class Food {
    private long id;
    private String name;
    private String description;
    private float price;
    private String type;
    private boolean isVegan;
    private Date createdAt;
    private Date updatedAt;


    public Food() {
    }

    public Food(String name, String description, float price, String type, boolean isVegan) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.isVegan = isVegan;
    }

    public Food(String name, String description, float price, String type, boolean isVegan, Date createdAt, Date updatedAt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.isVegan = isVegan;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Food(long id, String name, String description, float price, String type, boolean isVegan, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.isVegan = isVegan;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", isVegan=" + isVegan +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
