package com.siriusxm.example.cart;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {

    @Test
    public void testAddProduct() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product("test", BigDecimal.TEN);
        cart = cart.addProduct(product, 1);
        assertEquals(1, cart.getProducts().size());
    }

    @Test
    public void testAddMultipleProducts() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product("test", BigDecimal.TEN);
        cart = cart.addProduct(product, 3);
        assertEquals(3, cart.getProducts().size());
    }

    @Test
    public void testGetSubtotal() {
        ShoppingCart cart = new ShoppingCart();
        Product product1 = new Product("test1", BigDecimal.TEN);
        Product product2 = new Product("test2", new BigDecimal("5.50"));
        cart = cart.addProduct(product1, 2);
        cart = cart.addProduct(product2, 1);
        assertEquals(new BigDecimal("25.50"), cart.getSubtotal());
    }

    @Test
    public void testGetTax() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product("test", new BigDecimal("10.00"));
        cart = cart.addProduct(product, 2);
        assertEquals(new BigDecimal("2.50"), cart.getTax());
    }

    @Test
    public void testGetTotal() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product("test", new BigDecimal("10.00"));
        cart = cart.addProduct(product, 2);
        assertEquals(new BigDecimal("22.50"), cart.getTotal());
    }
}
