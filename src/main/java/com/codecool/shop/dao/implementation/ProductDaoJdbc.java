package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.List;

/**
 * Created by jakab on 2016.11.29..
 */
public class ProductDaoJdbc implements ProductDao{

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private String query;
    public Product product;
    private static ProductDaoJdbc instance = null;

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Product product) {

        query = "INSERT INTO product (name, defaultPrice, defaultCurrency, productCategory, supplier) VALUES (?, ?, ?, ?, ?);";
        try (Connection connection = getConnection()) {
            ;
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, product.getName());
            stmt.setFloat(2, product.getDefaultPrice());
            stmt.setString(3, product.getDefaultCurrency().toString());
            stmt.setInt(4, product.getProductCategory().getId());
            stmt.setInt(5, product.getSupplier().getId());


            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Product find(int id) throws SQLException {

        String query = "SELECT * FROM product WHERE id ='" + id + "';";
        ProductCategoryDao productCategoryDao = ProductCategoryDaoJdbc.getInstance();
        SupplierDao supplierDao = SupplierDaoJdbc.getInstance();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                ProductCategory category = productCategoryDao.find(resultSet.getInt("category_id"));
                Supplier supplier = supplierDao.find(resultSet.getInt("supplier_id"));
                Product result = new Product(resultSet.getString("name"),
                        resultSet.getFloat("defaultprice"),
                        resultSet.getString("defaultcurrency"),
                        resultSet.getString("description"),
                        category,
                        supplier);
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
        query = "DELETE FROM suppliers WHERE id = " + id + ";";
        instance.executeQuery(query);
    }


    @Override
    public List<Product> getAll() {
        return null;
    }


    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }


    @Override
    public List<Product> getBy(ProductCategory productCategory) {
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
