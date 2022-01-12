package persistence;

import model.Inventory;
import model.Product;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
// Referenced sample code provided by instructors to understand JSON
// Represents a reader that reads inventoryList from JSON data stored in file

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads inventoryList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses inventoryList from JSON object and returns it
    private Inventory parseInventory(JSONObject jsonObject) {
        Inventory i = new Inventory();
        addProducts(i, jsonObject);
        return i;
    }

    // MODIFIES: i
    // EFFECTS: parses products from JSON object and adds them to inventory
    private void addProducts(Inventory i, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Inventory List");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addProduct(i, nextProduct);
        }
    }

    // MODIFIES: i
    // EFFECTS: parses products from JSON object and adds it to inventory
    private void addProduct(Inventory i, JSONObject jsonObject) {
        String productName = jsonObject.getString("productName");
        int inventoryAvailable = jsonObject.getInt("inventoryAvailable");
        int sellingPrice = jsonObject.getInt("sellingPrice");
        int costPrice = jsonObject.getInt("costPrice");
        int grossMargin = jsonObject.getInt("grossMargin");
        Product product = new Product(productName, inventoryAvailable, sellingPrice, costPrice);
        i.addProduct(product);
    }
}


