package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        // tomi commit

        Map params = new HashMap<>();
        params.put("category", "All products");
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());

        params.put("supplier", "All suppliers");
        params.put("suppliers", )
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderCategory(Request req, Response res) {
        String categoryId = req.params(":category_id");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(Integer.parseInt(categoryId)));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(categoryId))));
        return new ModelAndView(params, "product/category");
    }

}
