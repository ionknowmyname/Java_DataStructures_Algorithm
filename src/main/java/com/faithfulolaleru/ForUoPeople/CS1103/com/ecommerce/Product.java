package com.faithfulolaleru.ForUoPeople.CS1103.com.ecommerce;

public class Product {
    private int productID;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;

    public Product(int productID, String name, double price, String description, int stockQuantity) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    // Getters
    public int getProductID() { return productID; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public int getStockQuantity() { return stockQuantity; }

    // Setters
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        this.stockQuantity = stockQuantity;
    }

    public boolean isAvailable() {
        return stockQuantity > 0;
    }

    @Override
    public String toString() {
        return String.format("Product[ID=%d, Name='%s', Price=%.2f, Stock=%d]",
                productID, name, price, stockQuantity);
    }
}
