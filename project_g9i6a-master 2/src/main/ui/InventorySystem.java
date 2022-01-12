package ui;

import model.Inventory;
import model.Product;
import model.Revenue;
import model.Sales;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

//Inventory System application
public class InventorySystem {

    private static final String JSON_STORE = "./data/inventory.json";
    private Inventory inventoryList;
    private Product product;
    private Scanner input;
    private Sales sale1;
    private Revenue saleList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public InventorySystem() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runSystem();
    }

    //MODIFIES: this
    // EFFECTS: processes the user input
    private void runSystem() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    @SuppressWarnings("methodlength")
    private void processCommand(String command) {
        switch (command) {
            case "a":
                doAddProduct();
                break;
            case "r":
                doRemoveProduct();
                break;
            case "s":
                doSearch();
                break;
            case "rs":
                doAddSale();
                break;
            case "rev":
                doRevReport();
                break;
            case "save":
                saveInventory();
                break;
            case "load":
                loadInventory();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }


    private void init() {
        Product product1 = new Product("Linen", 500, 330, 200);
        inventoryList = new Inventory();
        saleList = new Revenue();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add a product to the inventory");
        System.out.println("\tr -> remove a product from the inventory");
        System.out.println("\ts -> search a product from the inventory");
        System.out.println("\trs -> Record a Sale");
        System.out.println("\trev -> get revenue report");
        System.out.println("\tsave -> To save the Inventory List");
        System.out.println("\tload -> Load saved Inventory List");
        System.out.println("\tq -> quit");
    }

    private void doRemoveProduct() {
        Scanner r = new Scanner(System.in);
        System.out.print("Enter Product Name:");
        String productName = r.nextLine();
        System.out.println(inventoryList.removeProduct(productName));

    }

    private void doSearch() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Product name:");
        String productName = s.nextLine();
        System.out.println(inventoryList.searchProduct(productName).getProductName());
        System.out.println("Name:" + inventoryList.searchProduct(productName).getProductName() + " Inventory available:"
                + inventoryList.searchProduct(productName).getInventoryAvailable()
                + " Selling Price:" + inventoryList.searchProduct(productName).getSellingPrice()
                + " Cost Price:" + inventoryList.searchProduct(productName).getCostPrice()
                + " Gross Profit" + inventoryList.searchProduct(productName).getGrossMargin());
    }

    private void doAddProduct() {
        Scanner pn = new Scanner(System.in);
        System.out.print("Enter Product Name:");
        String productName = pn.nextLine();

        Scanner q = new Scanner(System.in);
        System.out.println("Enter quantity:");
        int quantity = q.nextInt();

        Scanner sp = new Scanner(System.in);
        System.out.println("Enter selling price:");
        int sellingPrice = sp.nextInt();

        Scanner cp = new Scanner(System.in);
        System.out.println("Enter cost price:");
        int costPrice = cp.nextInt();

        product = new Product(productName, quantity, sellingPrice, costPrice);
        String result = inventoryList.addProduct(product);
        System.out.println(result);
    }

    private void doAddSale() {

        Scanner ps = new Scanner(System.in);
        System.out.println("Enter product sold:");
        String productSold = ps.nextLine();

        Scanner sv = new Scanner(System.in);
        System.out.println("Enter Quantity:");
        int quantity = sv.nextInt();


        sale1 = new Sales(quantity, inventoryList.searchProduct(productSold));
        String result = saleList.addSale(sale1);
        System.out.println(result);
    }

    private void doRevReport() {
        System.out.println(saleList.revenueReport());
    }


    // EFFECTS: saves the workroom to file
    private void saveInventory() {
        try {
            jsonWriter.open();
            jsonWriter.write(inventoryList);
            jsonWriter.close();
            System.out.println("Saved inventory list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadInventory() {
        try {
            inventoryList = jsonReader.read();
            System.out.println("Loaded the inventory list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}


