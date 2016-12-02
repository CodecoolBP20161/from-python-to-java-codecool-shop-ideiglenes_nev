package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.ProductCategory;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
/**
 * Created by jakab on 2016.11.30..
 */
public class ProductDaoMemTest {

    private ProductDao productDao;
    private List<Product> testData = new ArrayList<>();
    private Supplier Supplier1;
    private ProductCategory Category1;
    private Product Product2;
    private List<Product> testProduct1InList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        productDao = ProductDaoMem.getInstance();
        Supplier supplier1 = new Supplier("sname1", "description1");
        Supplier supplier2 = new Supplier("sname2", "description2");
        ProductCategory productCategory1 = new ProductCategory("cname1", "department1", "description1");
        ProductCategory productCategory2 = new ProductCategory("cname2", "department2", "description2");
        Product product1 = new Product("pname1", 500, "EUR", "productdesc1", productCategory1, supplier1);
        Product product2 = new Product("pname2", 800, "EUR", "productdesc2", productCategory2, supplier2);

        productDao.add(product1);
        productDao.add(product2);
        testData.add(product1);
        testData.add(product2);
        Supplier1 = supplier1;
        Category1 = productCategory1;
        Product2 = product2;
        testProduct1InList.add(product1);
    }
    @After
    public void tearDown() throws Exception {
        for (int i = productDao.getAll().size(); i > 0; i--){
            productDao.remove(i);
        }
    }
    @Test
    public void add() throws Exception {
        Supplier supplier3 = new Supplier("sname3", "description3");
        ProductCategory productCategory3 = new ProductCategory("cname3", "department3", "description3");
        Product testProduct3 = new Product("pname3", 500, "EUR", "description3", productCategory3, supplier3);

        testData.add(testProduct3);
        productDao.add(testProduct3);

        assertEquals(testData, productDao.getAll());
    }
    @Test
    public void getAll() throws Exception {
        assertEquals(testData, productDao.getAll());
    }
    @Test
    public void getBySupplier() throws Exception {
        assertEquals(testProduct1InList, productDao.getBy(Supplier1));
    }
    @Test
    public void getByCategory() throws Exception {
        assertEquals(testProduct1InList, productDao.getBy(Category1));
    }
    @Test
    public void find() throws Exception {
        assertEquals(Product2, productDao.find(2));
    }
    @Test
    public void remove() throws Exception {
        testData.remove(Product2);
        productDao.remove(2);

        assertEquals(testData, productDao.getAll());
    }
}