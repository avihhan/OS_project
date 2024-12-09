package com.example;

import java.util.ArrayList;
import java.util.List;

// Class to represent an eBay item
class EbayItem {
    String title;
    String itemId;
    double price;

    // Constructor to initialize an item
    public EbayItem(String title, String itemId, double price) {
        this.title = title;
        this.itemId = itemId;
        this.price = price;
    }

    // Method to display item details
    @Override
    public String toString() {
        return String.format("Title: %s | ID: %s | Price: $%.2f", title, itemId, price);
    }
}

public class EbaySearchDemo {
    public static void get_items() {
        // List to hold dummy eBay items
        List<EbayItem> items = new ArrayList<>();

        // Adding fake data
        items.add(new EbayItem("Vintage Watch", "EB12345", 99.99));
        items.add(new EbayItem("Laptop - 16GB RAM", "EB67890", 799.99));
        items.add(new EbayItem("Wireless Headphones", "EB11122", 59.99));
        items.add(new EbayItem("Smartphone 128GB", "EB33344", 499.99));
        items.add(new EbayItem("Camera - 24MP", "EB55566", 350.00));

        // Displaying the items
        System.out.println("eBay Search Results:");
        items.forEach(item -> System.out.println(item));
    }
}
