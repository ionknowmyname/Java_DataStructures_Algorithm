package com.faithfulolaleru.ForUoPeople;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Week2 {

    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.start();
    }

    static class Book {
        String title;
        String author;
        int quantity;

        public Book(String title, String author, int quantity) {
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }

        public void addQuantity(int amount) {
            this.quantity += amount;
        }

        public boolean borrowBook(int amount) {
            if (quantity >= amount) {
                quantity -= amount;
                return true;
            }
            return false;
        }

        public void returnBook(int amount) {
            quantity += amount;
        }
    }

    static class LibrarySystem {
        private final Map<String, Book> library = new HashMap<>();
        private final Scanner scanner = new Scanner(System.in);

        public void start() {
            while (true) {
                System.out.println("\nLibrary System Menu:");
                System.out.println("1. Add Books");
                System.out.println("2. Borrow Books");
                System.out.println("3. Return Books");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addBooks();
                        break;
                    case 2:
                        borrowBooks();
                        break;
                    case 3:
                        returnBooks();
                        break;
                    case 4:
                        System.out.println("Exiting the library system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }

        private void addBooks() {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            System.out.print("Enter book author: ");
            String author = scanner.nextLine();
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (library.containsKey(title)) {
                library.get(title).addQuantity(quantity);
                System.out.println("Updated the quantity of the book.");
            } else {
                library.put(title, new Book(title, author, quantity));
                System.out.println("Added the book to the library.");
            }
        }

        private void borrowBooks() {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            System.out.print("Enter quantity to borrow: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (library.containsKey(title)) {
                if (library.get(title).borrowBook(quantity)) {
                    System.out.println("Successfully borrowed the book(s).");
                } else {
                    System.out.println("Not enough copies available.");
                }
            } else {
                System.out.println("Book not found in the library.");
            }
        }

        private void returnBooks() {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            System.out.print("Enter quantity to return: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (library.containsKey(title)) {
                library.get(title).returnBook(quantity);
                System.out.println("Successfully returned the book(s).");
            } else {
                System.out.println("Book not found in the library.");
            }
        }
    }
}
