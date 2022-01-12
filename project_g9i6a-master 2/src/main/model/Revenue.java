package model;


import java.util.ArrayList;
import java.util.Arrays;

public class Revenue {

    private ArrayList<Sales> saleList;
    private int revenue;
    private int totalGrossProfit;

    //Constructs a sales list containing products
    //EFFECTS: creates an empty list of sales
    public Revenue() {
        saleList = new ArrayList<>();
    }

    public int getRevenue() {
        return revenue;
    }

    public int getTotalGrossProfit() {
        return totalGrossProfit;
    }

    //REQUIRES: A Sale that needs to be added to the list
    //MODIFIES: this
    //          Revenue: adds the revenue generated from the new sale
    //          TotalGrossProfit: adds the gross profit generated from the sale to the total gross profit
    //EFFECTS: adds a new sale to saleList
    public String addSale(Sales newSale) {
        if (newSale.getProductName().getInventoryAvailable() > newSale.getQuantity()) {
            int temp = newSale.getProductName().getInventoryAvailable() - newSale.getQuantity();
            newSale.getProductName().setInventoryAvailable(temp);
            if (newSale.getProductName().getInventoryAvailable() < 50) {
                return "Alert!" + newSale.getProductName() + "has Low Inventory:"
                        + newSale.getProductName().getInventoryAvailable();
            }
            saleList.add(newSale);
            revenue = revenue + (newSale.getProductName().getSellingPrice() * newSale.getQuantity());
            totalGrossProfit = totalGrossProfit + (newSale.getProductName().getGrossMargin() * newSale.getQuantity());
            EventLog.getInstance().logEvent(new Event("Sale was recorded for " + newSale.getProductName()));
            return "Success! Sale was recorded";

        } else {
            return "Error! Sale could not be recorded";
        }
    }

    // EFFECTS: returns the total revenue and gross profit
    public String revenueReport() {
        EventLog.getInstance().logEvent(new Event(" Revenue Report was requested"));
        return "Total Revenue:" + revenue + "and Total Gross Profit:" + totalGrossProfit;
    }
}



