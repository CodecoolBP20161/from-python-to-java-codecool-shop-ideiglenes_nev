package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
/**
 * Created by kadartamas on 2016.11.30..
 */
public class ProductCategoryDaoJdbcTest {
    private ProductCategoryDao ProductCategoryDao;
    private List<ProductCategory> testData = new ArrayList<>();
    private ProductCategory CategoryRemoveAndFind;

    @Before
    public void setUp() throws Exception {
        ProductCategoryDao = ProductCategoryDaoJdbc.getInstance();

        ProductCategory category1 = new ProductCategory("name1", "department1", "test1");
        ProductCategory category2 = new ProductCategory("name2", "department1", "test2");
        CategoryRemoveAndFind = new ProductCategory("name3", "department3", "test remove and find");
        ProductCategoryDao.add(category1);
        ProductCategoryDao.add(category2);
        ProductCategoryDao.add(CategoryRemoveAndFind);
        testData.add(category1);
        testData.add(category2);
        testData.add(CategoryRemoveAndFind);
    }
    @After
    public void tearDown() throws Exception {
        for(int i = ProductCategoryDao.getAll().size(); i > 0; i--){
            ProductCategoryDao.remove(i);
        }
    }
    @Test
    public void getAll() throws Exception {
        assertEquals(testData, ProductCategoryDao.getAll());
    }
    @Test
    public void add() throws Exception {
        ProductCategory category3 = new ProductCategory("testAdd", "department3", "test3");
        ProductCategoryDao.add(category3);
        testData.add(category3);
        assertEquals(testData, ProductCategoryDao.getAll());
    }
    @Test
    public void find() throws Exception {
        assertEquals(CategoryRemoveAndFind, ProductCategoryDao.find(3));
    }
    @Test
    public void remove() throws Exception {
        testData.remove(CategoryRemoveAndFind);
        ProductCategoryDao.remove(3);
        assertEquals(testData, ProductCategoryDao.getAll());
    }
}
