package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakab on 2016.11.29..
 */
public class ProductCategoryDaoJdbc implements ProductCategoryDao{

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private String query;
    private ProductCategory productCategory;
    private static ProductCategoryDaoJdbc instance = null;

    private ProductCategoryDaoJdbc() throws SQLException {
    }

    public static ProductCategoryDaoJdbc getInstance() throws SQLException {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

//    Connection connection = getConnection();

    @Override
    public void add(ProductCategory category) {

        String name = category.getName();
        String department = category.getDepartment();
        String description = category.getDescription();

        query = "INSERT INTO product_category (name, department, description) VALUES ('" + name + "','" + department + "','" + description + "')";
        instance.executeQuery(query);
    }

    @Override
    public ProductCategory find(int id) {

        query = "SELECT * FROM product_category WHERE id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                ProductCategory result = new ProductCategory(resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                return result;
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

        query = "DELETE FROM product_category WHERE id = '" + id + "';";
        instance.executeQuery(query);
    }

    @Override
    public List<ProductCategory> getAll() {

        query = "SELECT * FROM product_category;";
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            List<ProductCategory> retCategoryList = new ArrayList<>();

            while (resultSet.next()){
                productCategory= new ProductCategory(resultSet.getString("name"),resultSet.getString("department"),resultSet.getString("description"));
                productCategory.setId(resultSet.getInt("id"));
                retCategoryList.add(productCategory);
            }
            return retCategoryList;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
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
