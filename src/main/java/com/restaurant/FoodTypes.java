package com.restaurant;

public enum FoodTypes {
    CHEAP("CHEAP"),
    EXPENSIVE("EXPENSIVE"),
    NORMAL("NORMAL");

    private final String name;

    FoodTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FoodTypes{" +
                "name='" + name + '\'' +
                '}';
    }
}
