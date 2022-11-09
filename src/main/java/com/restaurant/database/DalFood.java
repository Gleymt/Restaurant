package com.restaurant.database;

import com.restaurant.Food;
import com.restaurant.utils.Builders;
import com.restaurant.FoodColumns;
import com.restaurant.utils.Helpers;
import com.restaurant.utils.Validations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

public class DalFood implements DalBase {

    private final static Scanner myObj = new Scanner(System.in);

    String pattern = "^[A-Za-z\\s !.?',~`:\"]*$";

    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static long time = System.currentTimeMillis();

    private final static Logger log = Logger.getLogger(DalFood.class.getName());
    private final static String basePath = System.getProperty("user.dir");
    private final static String dbInfoPath = "/src/main/resources/DatabaseInfo";
    private final static Properties properties = new Properties();
    private static String root;
    public static String username;
    private static String password;
    public static String restaurantDatabase;

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet result = null;

    public DalFood() throws IOException {
        properties.load(new FileInputStream(basePath + dbInfoPath));
        root = properties.getProperty("database");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        restaurantDatabase = properties.getProperty("dbname");
        connect();
    }

    @Override
    public void sortAllFoodByName() throws SQLException, IOException {
        String sqlString = "select * from food order by name";
        result = statement.executeQuery(sqlString);

        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }

        addToLog(sqlString, time);
    }

    @Override
    public void sortAllFoodByPrice() throws SQLException, IOException {
        String sqlString = "select * from food order by price";
        result = statement.executeQuery(sqlString);

        while (result.next()) {
            System.out.println(result.getString(FoodColumns.NAME.name()) + ", " + result.getString(FoodColumns.PRICE.name()));
        }
        addToLog(sqlString, time);
    }

    @Override
    public Food selectFoodById(long id) throws SQLException, IOException {
        //String sqlString = "select * from food where id=?";
        PreparedStatement statement = connection.prepareStatement("select * from food where id = ?");
        statement.setString(1, String.valueOf(id));

        ResultSet result = statement.executeQuery();
        Food food = new Food();
        food.setId(id);
        while (result.next()) {
            food.setName(result.getString(FoodColumns.NAME.name()));
            food.setDescription(result.getString(FoodColumns.DESCRIPTION.name()));
            food.setPrice(result.getFloat(FoodColumns.PRICE.name()));
            food.setType(result.getString(FoodColumns.TYPE.name()));
            food.setVegan(result.getBoolean(FoodColumns.IS_VEGAN.name()));
            food.setCreatedAt(result.getDate(FoodColumns.CREATED_AT.name()));
            food.setUpdatedAt(result.getDate(FoodColumns.UPDATED_AT.name()));
        }

        addToLog(String.valueOf(statement), time);
        System.out.printf("Selected %s.\n", food);
        return food;
    }

    @Override
    public Food selectFoodByName(String name) throws SQLException, IOException {
        //String sqlString = "select * from food where name=?";
        PreparedStatement statement = connection.prepareStatement("select * from food where name = ?");
        statement.setString(1, String.valueOf(name));

        ResultSet result = statement.executeQuery();
        Food food = new Food();
        food.setName(name);
        while (result.next()) {
            food.setId(result.getInt(FoodColumns.ID.name()));
            food.setDescription(result.getString(FoodColumns.DESCRIPTION.name()));
            food.setPrice(result.getFloat(FoodColumns.PRICE.name()));
            food.setType(result.getString(FoodColumns.TYPE.name()));
            food.setVegan(result.getBoolean(FoodColumns.IS_VEGAN.name()));
            food.setCreatedAt(result.getDate(FoodColumns.CREATED_AT.name()));
            food.setUpdatedAt(result.getDate(FoodColumns.UPDATED_AT.name()));
        }
        addToLog(String.valueOf(statement), time);
        System.out.printf("Selected %s.\n", food);
        return food;
    }

    @Override
    public void addFood(Food food) throws SQLException, IOException {
        //String sqlString = "insert into food(name, description, price, type, is_vegan)" + "values(?, ?, ?, ?, ?)";
        PreparedStatement myStmt = connection.prepareStatement("insert into food(name, description, price, type, is_vegan)" + "values(?, ?, ?, ?, ?)");

        myStmt.setString(1, food.getName());
        myStmt.setString(2, food.getDescription());
        myStmt.setFloat(3, food.getPrice());
        myStmt.setString(4, food.getType());
        myStmt.setBoolean(5, food.isVegan());

        int rowsAffected = myStmt.executeUpdate();
        addToLog(String.valueOf(myStmt), time);
        log.info("Food: " + food.getName() + " was added. Rows affected: " + rowsAffected);
    }

    @Override
    public void updateFoodByID(long id) throws SQLException, IOException {

        Food food = selectFoodById(id);

        //String sqlString = "update food set name = ?, description = ?, price = ?, type = ?, is_vegan = ? where id = ?";
        PreparedStatement myStmt = connection.prepareStatement("update food set name = ?, description = ?, price = ?, type = ?, is_vegan = ? where id = ?");
        Food userInputFood = Builders.takeUserInputAndBuildFood();

        setSqlPreparedStatements(myStmt, userInputFood);
        myStmt.setLong(6, id);

        int rowsAffected = myStmt.executeUpdate();
        log.info(String.format("Food %s was updated to %s. Rows affected: %d", food.getName(), userInputFood.getName(), rowsAffected));
        result = statement.executeQuery("select * from food");
        addToLog(String.valueOf(myStmt), time);
    }

    @Override
    public void updateFoodByName(String name) throws SQLException, IOException {

        Food food = selectFoodByName(name);

        //String sqlString = "update food set name = ?, description = ?, price = ?, type = ?, is_vegan = ? where name = ?";
        PreparedStatement myStmt = connection.prepareStatement("update food set name = ?, description = ?, price = ?, type = ?, is_vegan = ? where name = ?");
        Food userInputFood = Builders.takeUserInputAndBuildFood();

        setSqlPreparedStatements(myStmt, userInputFood);
        myStmt.setString(6, name);

        int rowsAffected = myStmt.executeUpdate();
        log.info(String.format("Food %s - %s was updated to %s  - %s. Rows affected: %d", food.getName(), food.getDescription(), userInputFood.getName(), userInputFood.getDescription(), rowsAffected));
        result = statement.executeQuery("select * from food");
        addToLog(String.valueOf(myStmt), time);
    }

    @Override
    public void updateOnlyName(int id) throws SQLException, IOException {

        selectFoodById(id);
        //String sqlString = "update food set name = ? where id = ?";
        PreparedStatement myStmt = connection.prepareStatement("update food set name = ? where id = ?");

        System.out.print("Enter new food name: ");
        String newName = Validations.checkString(myObj, pattern, "name", 3, 100);
        myStmt.setString(1, newName);
        myStmt.setString(2, String.valueOf(id));
        int rowsAffected = myStmt.executeUpdate();
        System.out.println("rows affected " + rowsAffected);
        addToLog(String.valueOf(myStmt), time);

    }

    public void updateOnlyDescription(int id) throws SQLException, IOException {

        selectFoodById(id);
        //String sqlString = "update food set description = ? where id = ?";
        PreparedStatement myStmt = connection.prepareStatement("update food set description = ? where id = ?");

        System.out.print("Enter new food description: ");
        String newDescription = Validations.checkString(myObj, pattern, "description", 5, 200);
        myStmt.setString(1, newDescription);
        myStmt.setString(2, String.valueOf(id));
        int rowsAffected = myStmt.executeUpdate();
        System.out.println("rows affected " + rowsAffected);
        addToLog(String.valueOf(myStmt), time);
    }

    public void updateOnlyPrice(int id) throws SQLException, IOException {

        Food food = selectFoodById(id);
        //String sqlString = "update food set price = ?,type = ? where id = ?";
        PreparedStatement myStmt = connection.prepareStatement("update food set price = ?,type = ? where id = ?");

        System.out.print("Enter new food price: ");
        float newPrice = Validations.checkFloat(myObj, "price");
        Helpers.typeCheck(food);
        myStmt.setString(1, String.valueOf(newPrice));
        myStmt.setString(2, food.getType());
        myStmt.setString(3, String.valueOf(id));
        int rowsAffected = myStmt.executeUpdate();
        System.out.println("rows affected " + rowsAffected);
        addToLog(String.valueOf(myStmt), time);
    }

    public void updateOnlyVegan(int id) throws SQLException, IOException {

        selectFoodById(id);
        //String sqlString = "update food set is_vegan = ? where id = ?";
        PreparedStatement myStmt = connection.prepareStatement("update food set is_vegan = ? where id = ?");

        System.out.print("Enter new food vegan state: ");
        boolean vegan = Validations.checkBoolean(myObj, "is_vegan");
        myStmt.setBoolean(1, vegan);
        myStmt.setString(2, String.valueOf(id));
        int rowsAffected = myStmt.executeUpdate();
        System.out.println("rows affected " + rowsAffected);
        addToLog(String.valueOf(myStmt), time);
    }

    @Override
    public void typeList() throws SQLException, IOException {
        String sqlString = "select type, count(*) as count from food group by type";
        result = statement.executeQuery(sqlString);

        while (result.next()) {
            log.info(result.getString("type") + ", " + result.getString("count"));
        }
        addToLog(sqlString, time);
    }

    @Override
    public void deleteID(long id) throws SQLException, IOException {
        //String sqlString = "delete from food where id = ?";
        PreparedStatement myStmt = connection.prepareStatement("delete from food where id = ?");
        Food food = selectFoodById(id);
        myStmt.setLong(1, id);

        int rowsAffected = myStmt.executeUpdate();
        System.out.println(rowsAffected + " rows affected");

        System.out.println("You have deleted " + food.getName() + " from the database");
        addToLog(String.valueOf(myStmt), time);
    }

    @Override
    public void deleteName(String name) throws SQLException, IOException {
        //String sqlString = "delete from food where name = ?";
        PreparedStatement myStmt = connection.prepareStatement("delete from food where name = ?");
        Food food = selectFoodByName(name);
        myStmt.setString(1, name);

        int rowsAffected = myStmt.executeUpdate();
        System.out.println(rowsAffected + " rows affected");

        System.out.println("You have deleted " + food.getName() + " from the database");
        addToLog(String.valueOf(myStmt), time);
    }


    private void connect() {
        try {
            connection = DriverManager.getConnection(root + restaurantDatabase, username, password);
            log.info("Connection successful to: " + restaurantDatabase);
            statement = connection.createStatement();
        } catch (Exception e) {
            log.warning("Can not connect to the database: " + restaurantDatabase);
        }
    }

    public static void createFile(String database, String username, long time) {
        try {
            File myFile = new File("dal-queries_" + time + ".log");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(myFile.getName());
            myWriter.write(sdf3.format(time) + " Connection successful to: " + database);
            myWriter.write("\n");
            myWriter.write(sdf3.format(time) + " User connected: " + username);
            myWriter.close();
        } catch (
                IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void addToLog(String sqlCommand, long time) throws IOException {
        long dateTime = System.currentTimeMillis();
        FileWriter myWriter = new FileWriter("dal-queries_" + time + ".log", true);
        myWriter.write("\n" + sdf3.format(dateTime) + " Executed " + sqlCommand);
        System.out.println("One row added to logs");
        myWriter.close();
    }

    public static void closeLog(long time) throws IOException {
        long dateTime = System.currentTimeMillis();
        FileWriter myWriter = new FileWriter("dal-queries_" + time + ".log", true);
        myWriter.write("\n");
        myWriter.write(sdf3.format(dateTime) + " Connection closed");
        myWriter.close();
    }

    private void setSqlPreparedStatements(PreparedStatement preparedStatement, Food food) throws SQLException {
        preparedStatement.setString(1, food.getName());
        preparedStatement.setString(2, food.getDescription());
        preparedStatement.setFloat(3, food.getPrice());
        preparedStatement.setString(4, food.getType());
        preparedStatement.setBoolean(5, food.isVegan());
    }
}
