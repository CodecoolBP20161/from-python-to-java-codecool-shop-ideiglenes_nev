package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakab on 2016.11.17..
 */
public class Cart extends AbstractCart{
    public static int lineAll=0;
    public static ArrayList<LineItem> lineItems = new ArrayList<>();
    private int totalQuantity;
    public static float totalPrice=0;

    public static int getLineAll(){
        return lineAll;
    }

    public void add(Product product) {
        boolean in = false;
        for (LineItem item : lineItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.increaseQuantity(1);
                lineAll+=1;
                totalPrice += item.getProduct().getDefaultPrice();

                in = true;
            }
        }

        if (in == false) {
            lineItems.add(new LineItem(product, 1));
            lineAll+=1;
            totalPrice += product.getDefaultPrice();
        }
    }

    public int getTotalQuantity() {
        this.totalQuantity = 0;
        for (LineItem item : lineItems) {
            this.totalQuantity += item.getQuantity();
        }
        return this.totalQuantity;
    }

    public static List<LineItem> lineItems() {
        return lineItems;
    }

    public void removeProduct(Product product){
        for (LineItem item : lineItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.decreaseQuantity(1);
            }
        }
    }

    public static float getTotalPrice() {
        return totalPrice;
    }

}
