package com.faithfulolaleru.ForUoPeople.CS1103.week6;

public class LibraryItem<T> {

    private String title;
    private String author;
    private String itemID;
    private T additionalDetails;

    public LibraryItem(String title, String author, String itemID, T additionalDetails) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
        this.additionalDetails = additionalDetails;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getItemID() {
        return itemID;
    }

    public T getAdditionalDetails() {
        return additionalDetails;
    }

    @Override
    public String toString() {
        return "ID: " + itemID + " | Title: " + title + " | Author: " + author +
                (additionalDetails != null ? " | Details: " + additionalDetails : "");
    }
}
