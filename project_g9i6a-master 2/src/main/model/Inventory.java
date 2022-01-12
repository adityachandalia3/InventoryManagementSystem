package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import model.EventLog;
import model.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Inventory implements Writable {

    private ArrayList<Product> inventoryList; // Holds the list of products and their details
    private static final int HASH_CONSTANT = 13;


    //Constructs an inventory list containing products
    //EFFECTS: creates an empty list of Products
    public Inventory() {
        inventoryList = new ArrayList<>();
    }

    //REQUIRES: A Product that needs to be added to the list
    //MODIFIES: this
    //EFFECTS: adds a new product to the InventoryList
    public String addProduct(Product newProduct) {
        inventoryList.add(newProduct);
        EventLog.getInstance().logEvent(new Event(newProduct.getProductName() + " was added to the list"));
        return "Your product was added";

    }

    //REQUIRES: A Product that needs to be removed to the list
    //MODIFIES:  this
    //EFFECTS: removes a product to the InventoryList
    public String removeProduct(String newProduct) {
        inventoryList.remove(this.searchProduct(newProduct));
        EventLog.getInstance().logEvent(new Event(newProduct + "was removed from the list"));
        return "The item was removed!";
    }

    //REQUIRES: A Product that needs to be searched from the list
    //EFFECTS: searches a  product in the InventoryList
    public Product searchProduct(String newProduct) {
        EventLog.getInstance().logEvent(new Event(newProduct + " was searched from the list"));
        for (Product p : inventoryList) {
            if (newProduct.toLowerCase(Locale.ROOT).equals(p.getProductName().toLowerCase())) {
                return p;
            }
        }
        return null;
    }

    //EFFECTS: Finds the size of the inventory list
    public int listSize() {
        return inventoryList.size();
    }

    // EFFECTS: returns an  list of thingies in this workroom
    public List<Product> getProducts() {
        return inventoryList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Inventory List", inventoryList);
        return json;
    }

    public ArrayList<Product> getList() {
        return inventoryList;
    }



    // EFFECTS: returns things in this inventory list as a JSON array
//    private JSONArray productsToJson() {
//        JSONArray jsonArray = new JSONArray();
//
//        for (Product t : inventoryList) {
//            jsonArray.put(t.toJson());
//        }
//
//        return jsonArray;
//    }
}





