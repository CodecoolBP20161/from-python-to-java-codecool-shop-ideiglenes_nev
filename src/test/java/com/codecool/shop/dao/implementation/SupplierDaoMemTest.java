package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
/**
 * Created by jakab on 2016.11.30..
 */
public class SupplierDaoMemTest {
    private SupplierDao supplierDao;
    private List<Supplier> testData = new ArrayList<>();
    private Supplier supplierRemoveAndFind;

    @Before
    public void setUp() throws Exception {
        supplierDao = SupplierDaoMem.getInstance();

        Supplier supplier1 = new Supplier("name1", "test1");
        Supplier supplier2 = new Supplier("name2", "test2");
        supplierRemoveAndFind = new Supplier("name3", "test remove and find");
        supplierDao.add(supplier1);
        supplierDao.add(supplier2);
        supplierDao.add(supplierRemoveAndFind);
        testData.add(supplier1);
        testData.add(supplier2);
        testData.add(supplierRemoveAndFind);
    }
    @After
    public void tearDown() throws Exception {
        for(int i = supplierDao.getAll().size(); i > 0; i--){
            supplierDao.remove(i);
        }
    }
    @Test
    public void getAll() throws Exception {
        assertEquals(testData, supplierDao.getAll());
    }
    @Test
    public void add() throws Exception {
        Supplier supplier3 = new Supplier("testAdd", "test3");
        supplierDao.add(supplier3);
        testData.add(supplier3);
        assertEquals(testData, supplierDao.getAll());
    }
    @Test
    public void find() throws Exception {
        assertEquals(supplierRemoveAndFind, supplierDao.find(3));
    }
    @Test
    public void remove() throws Exception {
        testData.remove(supplierRemoveAndFind);
        supplierDao.remove(3);
        assertEquals(testData, supplierDao.getAll());
    }
}