package com.codecool.shop.model;

/**
 * Created by jakab on 2016.12.15..
 */

public class LineItem {
    private Product product;
    private int quantity;
    private float subTotalPrice;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public float getSubTotalPrice() {
        subTotalPrice = this.quantity * this.product.getDefaultPrice();
        return subTotalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity(int quantity) {
        if (this.quantity >= 1) {
            this.quantity -= quantity;
        }
    }
}
