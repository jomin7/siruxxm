package com.siriusxm.example.cart;

import java.io.IOException;
import java.math.BigDecimal;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        PriceFetcher priceFetcher = new PriceFetcher();
        ShoppingCart cart = new ShoppingCart();

        BigDecimal cornflakesPrice = priceFetcher.fetchPrice("cornflakes");
        Product cornflakes = new Product("cornflakes", cornflakesPrice);
        cart = cart.addProduct(cornflakes, 2);

        BigDecimal weetabixPrice = priceFetcher.fetchPrice("weetabix");
        Product weetabix = new Product("weetabix", weetabixPrice);
        cart = cart.addProduct(weetabix, 1);
        System.out.println("Subtotal: " + cart.getSubtotal());
        System.out.println("Tax: " + cart.getTax());
        System.out.println("Total: " + cart.getTotal());
    }
}
