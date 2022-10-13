package com.restaurant.database;

import com.restaurant.Food;

import java.sql.SQLException;

public interface DalBase {

    void showFood() throws SQLException;

    void addFood() throws SQLException;

    void sortFoodName() throws SQLException;

    void sortFoodPrice() throws SQLException;

    void typeList() throws SQLException;

    void deleteID() throws SQLException;

    void deleteName() throws SQLException;

    void updateFoodID() throws SQLException;

    void updateFoodName() throws SQLException;
    //void addFood() throws SQLException;
}
