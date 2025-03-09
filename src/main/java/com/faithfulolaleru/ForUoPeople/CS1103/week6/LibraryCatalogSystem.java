package com.faithfulolaleru.ForUoPeople.CS1103.week6;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LibraryCatalogSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Catalog<LibraryItem<?>> mainCatalog = new Catalog<>("Main Library Catalog");

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("===== Generic Library Catalog System =====");

        while (running) {
            try {
                printMenu();
                int choice = getUserChoice();

                switch (choice) {
                    case 1:
                        addNewItem();
                        break;
                    case 2:
                        removeItem();
                        break;
                    case 3:
                        searchItem();
                        break;
                    case 4:
                        displayAllItems();
                        break;
                    case 5:
                        running = false;
                        System.out.println("Thank you for using the Library Catalog System!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // Clear the scanner buffer
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Menu Options:");
        System.out.println("1. Add a new library item");
        System.out.println("2. Remove a library item");
        System.out.println("3. Search for an item");
        System.out.println("4. Display all items");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        return scanner.nextInt();
    }

    private static void addNewItem() {
        scanner.nextLine(); // Clear buffer

        System.out.println("\n=== Add New Item ===");
        System.out.println("Select item type:");
        System.out.println("1. Book");
        System.out.println("2. DVD");
        System.out.println("3. Magazine");
        System.out.print("Enter your choice: ");

        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter item ID: ");
        String itemID = scanner.nextLine();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author/creator: ");
        String author = scanner.nextLine();

        LibraryItem<?> newItem = null;

        switch (typeChoice) {
            case 1: // Book
                System.out.print("Enter page count: ");
                int pageCount = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter genre: ");
                String genre = scanner.nextLine();

                System.out.print("Enter ISBN: ");
                String isbn = scanner.nextLine();

                BookDetails bookDetails = new BookDetails(pageCount, genre, isbn);
                newItem = new LibraryItem<>(title, author, itemID, bookDetails);
                break;

            case 2: // DVD
                //  no impl. for now
                break;

            case 3: // Magazine
                //  no impl. for now
                break;

            default:
                System.out.println("Invalid item type selected.");
                return;
        }

        if (newItem != null) {
            mainCatalog.addItem(newItem);
            System.out.println("Item added successfully!");
        }
    }

    private static void removeItem() {
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter the ID of the item to remove: ");
        String itemID = scanner.nextLine();

        try {
            mainCatalog.removeItem(itemID);
            System.out.println("Item removed successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchItem() {
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter the ID of the item to search: ");
        String itemID = scanner.nextLine();

        try {
            LibraryItem<?> item = mainCatalog.getItem(itemID);
            System.out.println("\nItem found:");
            System.out.println(item);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayAllItems() {
        List<LibraryItem<?>> allItems = mainCatalog.getAllItems();

        if (allItems.isEmpty()) {
            System.out.println("The catalog is empty.");
            return;
        }

        System.out.println("\n=== " + mainCatalog.getCatalogName() + " ===");
        System.out.println("Total items: " + allItems.size());
        System.out.println("----------------------------------------");

        for (LibraryItem<?> item : allItems) {
            System.out.println(item);
        }
    }
}
