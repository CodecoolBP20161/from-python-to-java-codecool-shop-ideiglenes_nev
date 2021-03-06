package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) throws IOException, URISyntaxException, SQLException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStone = SupplierDaoMem.getInstance();


        Map params = new HashMap<>();
        params.put("category", "All categories");
        params.put("categories", productCategoryDataStore.getAll());
        params.put("supplier", "All suppliers");
        params.put("suppliers", supplierDataStone.getAll());
        params.put("products", productDataStore.getAll());
        params.put("supplier", "All suppliers");
        params.put("cart", Cart.getLineAll());
        params.put("top5", Top5Controller.getTop5(req, res));
        params.put("avatar", "http://" + AvatarController.getAvatar(req, res));

        req.session().attribute("lastURL", req.pathInfo());

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderCategory(Request req, Response res) throws IOException, URISyntaxException, SQLException {
        String categoryId = req.params(":category_id");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStone = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(Integer.parseInt(categoryId)));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(categoryId))));
        params.put("suppliers", supplierDataStone.getAll());
        params.put("supplier", "All suppliers");
        params.put("cart", Cart.getLineAll());
        params.put("avatar", "http://" + AvatarController.getAvatar(req, res));
        params.put("top5", Top5Controller.getTop5(req, res));


        req.session().attribute("lastURL", req.pathInfo());

        return new ModelAndView(params, "product/category");
    }
    public static ModelAndView renderSupplier(Request req, Response res) throws IOException, URISyntaxException, SQLException {
        String supplierId = req.params(":supplier_id");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStone = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("supplier", supplierDataStone.find(Integer.parseInt(supplierId)));
        params.put("suppliers", supplierDataStone.getAll());
        params.put("products", productDataStore.getBy(supplierDataStone.find(Integer.parseInt(supplierId))));
        params.put("category", "All categories");
        params.put("categories", productCategoryDataStore.getAll());
        params.put("cart", Cart.getLineAll());
        params.put("avatar", "http://" + AvatarController.getAvatar(req, res));
        params.put("top5", Top5Controller.getTop5(req, res));


        req.session().attribute("lastURL", req.pathInfo());

        return new ModelAndView(params, "product/supplier");
    }

    public static ModelAndView renderReview(Request req, Response res) throws IOException, URISyntaxException {
        Map params = new HashMap<>();
        params.put("lineItems", Cart.lineItems());
        params.put("cart", Cart.getLineAll());
        params.put("total", Cart.getTotalPrice());
        params.put("avatar", "http://" + AvatarController.getAvatar(req, res));

        req.session().attribute("lastURL", req.pathInfo());

        return new ModelAndView(params, "product/shopping_cart");
    }


}
