package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakab on 2016.11.29..
 */
public class SupplierDaoJdbc implements SupplierDao{

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private String query;

    private static SupplierDaoJdbc instance = null;

    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier){
        query = "INSERT INTO suppliers (name, description)" +
                "VALUES ('" + supplier.getName() + "', '" + supplier.getDescription() + "');";
        instance.executeQuery(query);
    }


    @Override
    public Supplier find(int id) {

        query = " SELECT * FROM suppliers WHERE id = '" + id +"';";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()){
                Supplier supplier = new Supplier(resultSet.getString("name"),
                        resultSet.getString("description"));
                supplier.setId(resultSet.getInt("id"));
                return supplier;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void remove(int id) {
        query = "DELETE FROM suppliers WHERE id = " + id + ";";
        instance.executeQuery(query);
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        query = "SELECT * FROM suppliers;";

        try {Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Supplier supplier = new Supplier(resultSet.getString("name"),resultSet.getString("description"));
                supplier.setId(resultSet.getInt("id"));
                suppliers.add(supplier);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return suppliers;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
