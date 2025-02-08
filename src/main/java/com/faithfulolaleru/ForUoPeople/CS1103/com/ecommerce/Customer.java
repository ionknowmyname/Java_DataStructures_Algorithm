package com.faithfulolaleru.ForUoPeople.CS1103.com.ecommerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    private int customerID;
    private String name;
    private Map<Product, Integer> shoppingCart;

    public Customer(int customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new HashMap<>();
    }

    // Getters
    public int getCustomerID() { return customerID; }
    public String getName() { return name; }

    public void addToCart(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (!product.isAvailable()) {
            throw new IllegalStateException("Product is out of stock");
        }
        if (quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        shoppingCart.merge(product, quantity, Integer::sum);
    }

    public void removeFromCart(Product product, int quantity) {
        if (!shoppingCart.containsKey(product)) {
            throw new IllegalArgumentException("Product not in cart");
        }

        int currentQuantity = shoppingCart.get(product);
        if (quantity >= currentQuantity) {
            shoppingCart.remove(product);
        } else {
            shoppingCart.put(product, currentQuantity - quantity);
        }
    }

    public double calculateCartTotal() {
        return shoppingCart.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public List<Map.Entry<Product, Integer>> getCartItems() {
        return new ArrayList<>(shoppingCart.entrySet());
    }

    public void clearCart() {
        shoppingCart.clear();
    }

    @Override
    public String toString() {
        return String.format("Customer[ID=%d, Name='%s']", customerID, name);
    }
}
