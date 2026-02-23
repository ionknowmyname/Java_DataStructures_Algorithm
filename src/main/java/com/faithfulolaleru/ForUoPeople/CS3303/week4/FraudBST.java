package com.faithfulolaleru.ForUoPeople.CS3303.week4;

public class FraudBST {
    Transaction root;

    Transaction insert(Transaction root, int id, String details) {
        if (root == null) {
            return new Transaction(id, details);
        }
        if (id < root.transactionId) {
            root.left = insert(root.left, id, details);
        } else if (id > root.transactionId) {
            root.right = insert(root.right, id, details);
        }
        return root;
    }

    void addTransaction(int id, String details) {
        root = insert(root, id, details);
    }

    Transaction search(Transaction root, int id) {
        if (root == null || root.transactionId == id) {
            return root;
        }
        if (id < root.transactionId) {
            return search(root.left, id);
        }
        return search(root.right, id);
    }

    void findTransaction(int id) {
        Transaction result = search(root, id);
        if (result != null) {
            System.out.println("Transaction FOUND -> ID: " + result.transactionId + " | Details: " + result.details);
        } else {
            System.out.println("Transaction ID " + id + " NOT FOUND in the system.");
        }
    }


    Transaction delete(Transaction root, int id) {
        if (root == null) return null;

        if (id < root.transactionId) {
            root.left = delete(root.left, id);
        } else if (id > root.transactionId) {
            root.right = delete(root.right, id);
        } else {
            // Leaf node
            if (root.left == null && root.right == null) return null;
            // One child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // Two children
            Transaction successor = findMin(root.right);
            root.transactionId = successor.transactionId;
            root.details = successor.details;
            root.right = delete(root.right, successor.transactionId);
        }
        return root;
    }

    void removeTransaction(int id) {
        root = delete(root, id);
    }

    Transaction findMin(Transaction root) {
        while (root.left != null) root = root.left;
        return root;
    }


    // to print
    void inOrder(Transaction root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println("  ID: " + root.transactionId + " | " + root.details);
            inOrder(root.right);
        }
    }

    void printAllTransactions() {
        System.out.println("Current Flagged Transactions (In-Order):");
        inOrder(root);
        System.out.println("------------------------------------------");
    }
}
