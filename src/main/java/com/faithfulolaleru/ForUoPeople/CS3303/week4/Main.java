package com.faithfulolaleru.ForUoPeople.CS3303.week4;

public class Main {
    public static void main(String[] args) {
        FraudBST bst = new FraudBST();

        // Adding to BST
        System.out.println("========== INSERTING TRANSACTIONS ==========");
        bst.addTransaction(500, "High-value withdrawal - $50,000");
        bst.addTransaction(300, "Rapid multiple transfers - Account A");
        bst.addTransaction(700, "Unauthorized access - Foreign IP");
        bst.addTransaction(200, "Failed login attempts x10");
        bst.addTransaction(400, "Duplicate transaction detected");
        bst.addTransaction(600, "Large overseas transfer");
        bst.addTransaction(800, "New device login - unrecognized location");
        System.out.println();

        bst.printAllTransactions();

        // searching
        System.out.println("========== SEARCHING TRANSACTIONS ==========");
        bst.findTransaction(300);   // should be FOUND
        bst.findTransaction(700);   // should be FOUND
        bst.findTransaction(999);   // should NOT be found
        System.out.println();

        // Deleting
        System.out.println("========== DELETING TRANSACTIONS ==========");

        // Case 1: Delete a LEAF node (no children)
        bst.removeTransaction(200);
        bst.printAllTransactions();

        // Case 2: Delete a node with ONE child
        bst.removeTransaction(800);
        bst.printAllTransactions();

        // Case 3: Delete a node with TWO children
        bst.removeTransaction(300);
        bst.printAllTransactions();

        // Try deleting a non-existent transaction
        bst.removeTransaction(999);
    }
}
