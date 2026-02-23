package com.faithfulolaleru.ForUoPeople.CS3303.week4;

public class Transaction {
    int transactionId;
    String details;
    Transaction left, right;

    Transaction(int id, String details) {
        this.transactionId = id;
        this.details = details;
        left = right = null;
    }
}
