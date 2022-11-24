package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        List<Product> availableProducts = getListOfAvailableProducts(products);
        return calculateProductsPrice(availableProducts);
    }

    private List<Product> getListOfAvailableProducts(List<Product> products) {
        List<Product> availableProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isAvailable()) {
                availableProducts.add(product);
            }
        }
        return availableProducts;
    }

    private double calculateProductsPrice(List<Product> products) {
        double orderPrice = 0.0;
        for (Product product : products) {
            orderPrice += product.getProductPrice();
        }
        return orderPrice;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
