package com.faithfulolaleru.ForUoPeople.CS1103.com.ecommerce.orders;

import com.faithfulolaleru.ForUoPeople.CS1103.com.ecommerce.Customer;
import com.faithfulolaleru.ForUoPeople.CS1103.com.ecommerce.Product;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderID;
    private Customer customer;
    private Map<Product, Integer> orderedProducts;
    private double orderTotal;
    private LocalDateTime orderDate;
    private OrderStatus status;

    public enum OrderStatus {
        PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    }

    public Order(int orderID, Customer customer, Map<Product, Integer> products) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderedProducts = new HashMap<>(products);
        this.orderTotal = calculateTotal();
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    // Getters
    public int getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public Map<Product, Integer> getOrderedProducts() { return new HashMap<>(orderedProducts); }
    public double getOrderTotal() { return orderTotal; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public OrderStatus getStatus() { return status; }

    private double calculateTotal() {
        return orderedProducts.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public void updateStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cannot update a cancelled order");
        }
        this.status = newStatus;
    }

    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append(String.format("Order ID: %d\n", orderID));
        summary.append(String.format("Customer: %s\n", customer.getName()));
        summary.append(String.format("Order Date: %s\n", orderDate));
        summary.append(String.format("Status: %s\n", status));
        summary.append("Ordered Items:\n");

        orderedProducts.forEach((product, quantity) ->
                summary.append(String.format("- %s x%d = $%.2f\n",
                        product.getName(), quantity, product.getPrice() * quantity))
        );

        summary.append(String.format("\nTotal Amount: $%.2f", orderTotal));
        return summary.toString();
    }
}
