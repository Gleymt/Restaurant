package com.restaurant.database;

import com.restaurant.Food;

import java.sql.SQLException;

public interface DalBase {

    void showAllFood() throws SQLException;

    void sortAllFoodByName() throws SQLException;

    void sortAllFoodByPrice() throws SQLException;

    void addFood(Food food) throws SQLException;

    void typeList() throws SQLException;

    void deleteID(long id) throws SQLException;

    void deleteName(String name) throws SQLException;

    void updateFoodID(long id) throws SQLException;

    void updateFoodName(String name) throws SQLException;

   // Food selectFoodById(long id) throws SQLException;

    // Food selectFoodByName(String name) throws SQLException;

   // boolean isNameUnique(String name) throws SQLException;


}
