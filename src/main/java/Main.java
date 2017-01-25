import com.codecool.shop.controller.CartController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.Top5Controller;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());

        get("/category/:category_id", ProductController::renderCategory, new ThymeleafTemplateEngine());
        get("/supplier/:supplier_id", ProductController::renderSupplier, new ThymeleafTemplateEngine());
        post("/addtocart/:product_id", CartController::postTop5);
        get("/addtocart/:product_id", CartController::addToCart, new ThymeleafTemplateEngine());
        get("/shopping_cart", ProductController::renderReview, new ThymeleafTemplateEngine());

        get("/gettop5", Top5Controller::getTop5);


        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        Supplier apple = new Supplier("Apple", "Mobile");
        supplierDataStore.add(apple);
        Supplier samsung = new Supplier("Samsung", "Mobile");
        supplierDataStore.add(samsung);
        Supplier razer = new Supplier("Razer", "Computer peripherial devices.");
        supplierDataStore.add(razer);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory keyboard = new ProductCategory("Keyboard", "Hardware", "A keyboard");
        productCategoryDataStore.add(keyboard);
        ProductCategory mobile = new ProductCategory("Mobile", "Hardware", "A mobile, you can call anybody");
        productCategoryDataStore.add(mobile);
        ProductCategory headphone = new ProductCategory("Headphone", "Hardware", "Headphones for computers.");
        productCategoryDataStore.add(headphone);


        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Samsung Galaxy A5", 170, "USD", "The perfect combination of metal and glass.", mobile, samsung));
        productDataStore.add(new Product("Apple iPhone 7", 799, "USD", "The revolution of mobilephone.", mobile, apple));
        productDataStore.add(new Product("Samsung Galaxy Note 7", 390, "USD", "Explodingly awesome.", mobile, samsung));
        productDataStore.add(new Product("Amazon keyboard", 20, "USD", "Used for typing.", keyboard, amazon));
        productDataStore.add(new Product("Razer Deathstalker", 80, "USD", "Fully programmable slim chiclet keycaps.", keyboard, razer));
        productDataStore.add(new Product("Apple Keyboard", 50, "USD", "The Apple Keyboard with Numeric Keypad.", keyboard, apple));
        productDataStore.add(new Product("Razer Kraken 7.1", 80, "USD", "The perfect balance of weight, functionality, and performance, the Razer Kraken is undisputedly the most popular E-sports gaming headset out there.", headphone, razer));
    }


}
