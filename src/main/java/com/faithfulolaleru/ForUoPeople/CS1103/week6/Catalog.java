package com.faithfulolaleru.ForUoPeople.CS1103.week6;

import java.util.ArrayList;
import java.util.List;

public class Catalog<T extends LibraryItem<?>> {
    private List<T> items;
    private String catalogName;

    public Catalog(String catalogName) {
        this.catalogName = catalogName;
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void removeItem(String itemID) throws Exception {
        boolean removed = items.removeIf(item -> item.getItemID().equals(itemID));
        if (!removed) {
            throw new Exception("Item with ID " + itemID + " not found in the catalog.");
        }
    }

    public T getItem(String itemID) throws Exception {
        for (T item : items) {
            if (item.getItemID().equals(itemID)) {
                return item;
            }
        }
        throw new Exception("Item with ID " + itemID + " not found in the catalog.");
    }

    public List<T> getAllItems() {
        return new ArrayList<>(items);
    }

    public int getItemCount() {
        return items.size();
    }

    public String getCatalogName() {
        return catalogName;
    }
}
