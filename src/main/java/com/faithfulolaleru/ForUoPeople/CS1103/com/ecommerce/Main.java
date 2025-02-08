package com.faithfulolaleru.ForUoPeople.CS1103.com.ecommerce;

import com.faithfulolaleru.ForUoPeople.CS1103.com.ecommerce.orders.Order;

import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            // Create products
            Product laptop = new Product(1, "Laptop", 1000, "High-performance laptop", 10);
            Product phone = new Product(2, "Smartphone", 600, "Latest smartphone", 15);
            Product tablet = new Product(3, "Tablet", 400, "10-inch tablet", 20);

            // Create customer
            Customer customer = new Customer(1, "Faithful Olaleru");

            // Add products to cart
            System.out.println("Adding products to cart...");
            customer.addToCart(laptop, 1);
            customer.addToCart(phone, 2);

            // Display cart contents
            System.out.println("\nShopping Cart:");
            customer.getCartItems().forEach(entry ->
                    System.out.printf("- %s x%d\n", entry.getKey().getName(), entry.getValue())
            );
            System.out.printf("Cart Total: $%.2f\n", customer.calculateCartTotal());

            // Create order
            Order order = new Order(1, customer, customer.getCartItems().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue
                    )));

            // Display order summary
            System.out.println("\nOrder Summary:");
            System.out.println(order.generateOrderSummary());

            // Update order status
            order.updateStatus(Order.OrderStatus.CONFIRMED);
            System.out.println("\nOrder status updated to: " + order.getStatus());

            // Clear customer's cart after order
            customer.clearCart();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
