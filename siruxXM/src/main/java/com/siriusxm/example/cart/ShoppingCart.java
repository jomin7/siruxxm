package com.siriusxm.example.cart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    private ShoppingCart(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public ShoppingCart addProduct(Product product, int quantity) {
        List<Product> newProducts = new ArrayList<>(this.products);
        for (int i = 0; i < quantity; i++) {
            newProducts.add(product);
        }
        return new ShoppingCart(newProducts);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public BigDecimal getSubtotal() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTax() {
        return getSubtotal().multiply(new BigDecimal("0.125")).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getTax()).setScale(2, RoundingMode.HALF_UP);
    }
}
