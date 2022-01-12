package model;

import org.json.JSONObject;
import persistence.Writable;

public class Product implements Writable {

    private String productName;      // tracks the name of the product
    private int inventoryAvailable;  // Keeps a track of the amount available
    private int sellingPrice;        // Holds the selling price of the product
    private int costPrice;           // Holds the cost price of the product
    private int grossMargin;         // Selling price - Cost price to find the gross margin


    public Product(String productName, int inventoryAvailable, int sellingPrice, int costPrice) {

        this.productName = productName;
        this.inventoryAvailable = inventoryAvailable;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
        this.grossMargin = sellingPrice - costPrice;

    }

    public String getProductName() {
        return productName;
    }

    public int getInventoryAvailable() {
        return inventoryAvailable;
    }

    public void setInventoryAvailable(int inventoryAvailable) {
        this.inventoryAvailable = inventoryAvailable;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public int getGrossMargin() {
        return grossMargin;
    }

    public String toString() {
        return productName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Product name", productName);
        json.put("Inventory available", inventoryAvailable);
        json.put("Selling Price", sellingPrice);
        json.put("Cost Price", costPrice);
        json.put("Gross Margin", grossMargin);
        return json;
    }
}
