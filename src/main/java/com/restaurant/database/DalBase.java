package com.restaurant.database;

import com.restaurant.Food;

import java.io.IOException;
import java.sql.SQLException;

public interface DalBase {

    void sortAllFoodByName() throws SQLException, IOException;

    void sortAllFoodByPrice() throws SQLException, IOException;

    void addFood(Food food) throws SQLException, IOException;

    void typeList() throws SQLException, IOException;

    void deleteID(long id) throws SQLException, IOException;

    void deleteName(String name) throws SQLException, IOException;

    void updateFoodByID(long id) throws SQLException, IOException;

    void updateFoodByName(String name) throws SQLException, IOException;

    Food selectFoodById(long id) throws SQLException, IOException;

    Food selectFoodByName(String name) throws SQLException, IOException;

    void updateOnlyVegan(int id) throws SQLException, IOException;

    void updateOnlyName(int id) throws SQLException, IOException;

    void updateOnlyDescription(int id) throws SQLException, IOException;

    void updateOnlyPrice(int id) throws SQLException, IOException;


}
