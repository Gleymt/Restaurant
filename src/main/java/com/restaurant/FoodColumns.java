package com.restaurant;

public enum FoodColumns {
    ID("id"),
    NAME("name"),
    DESCRIPTION("description"),
    PRICE("price"),
    TYPE("type"),
    IS_VEGAN("is_vegan"),
    CREATED_AT("created_at"),
    UPDATED_AT("updated_at");

    private final String name;

    FoodColumns(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FoodColumns{" +
                "name='" + name + '\'' +
                '}';
    }
}
