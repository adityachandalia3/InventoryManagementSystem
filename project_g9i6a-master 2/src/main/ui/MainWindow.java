package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainWindow extends JFrame implements ActionListener {

    private static final int HEIGHT = 600;
    private static final int WIDTH = 900;
    private JLabel pageTitle;
    private JList<String> displayFrame;
    private DefaultListModel<String> listModel;

    //JSON Elements
    private static final String JSON_STORE = "./data/inventory.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame loadListFrame;
    @SuppressWarnings({"checkstyle:MemberName", "checkstyle:SuppressWarnings"})
    private JPanel MainWindowPanel;
    private JButton loadList;
    private JButton savelist;
    private Inventory inventoryList;
    private Revenue salesList;

    //Add Product
    private JButton addProduct;
    private JTextField productName;
    private JTextField productCostPrice;
    private JTextField productSellingPrice;
    private JTextField inventoryAvailable;
    private Product product;

    //Remove Product
    private JButton removeProduct;
    private JTextField removeProductName;
    private String removeTextField;

    //search Product
    private JButton search;
    private JTextField searchField;
    private JTextArea searchResult;

    //add sale
    private JButton addSale;
    private JTextField saleProductName;
    private JTextField quantity;
    private Sales sale1;

    //Revenue Report
    private JButton revReport;
    private JTextField revenue;
    private JTextField grossProfit;

    // JTable
    private JTable listTable;
    private String columnNames;
    private Object data;
    private JScrollPane scrollPane;

    //Logo
    private ImageIcon logo;
    private JLabel image;



    private WindowListener windowListenerWindow = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            printLogEvent();
        }
    };

    public void printLogEvent() {
        EventLog logtoPrint = EventLog.getInstance();
        for (Event i : logtoPrint) {
            System.out.println(i.getDate() + " " + i.getDescription());
        }
    }



    // EFFECTS: Constructs the Main window
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public MainWindow() {
        super("Inventory Management System: Main Window");
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainWindowPanel = new JPanel();
        MainWindowPanel.setSize(900, 600);
        MainWindowPanel.setLayout(null);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        listModel = new DefaultListModel<>();
        listModel.addElement(" ");

        salesList = new Revenue();

        logo = new ImageIcon("src/main/ui/AdityaRayonLogo.png");
        image = new JLabel("logo");
        image.setIcon(logo);

        addWindowListener(windowListenerWindow);
        addComponents();
        add(MainWindowPanel);
        setVisible(true);

    }

    // EFFECTS: Adds components to the Main window
    // MODIFIES: JFrame
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addComponents() {

        //Load Button
        loadList = new JButton("Load List");
        loadList.setActionCommand("Load List");
        loadList.addActionListener(this);

        //Save Button
        savelist = new JButton(("Save List"));
        savelist.setActionCommand("save");
        savelist.addActionListener(this);

        //Add Product Button
        addProduct = new JButton("Add Product");
        addProduct.setActionCommand("add");
        addProduct.addActionListener(this);

        productName = new JTextField("Enter Product Name");
        productName.setActionCommand("pn");
        productName.addActionListener(this);

        inventoryAvailable = new JTextField("Enter Inventory Available");
        inventoryAvailable.setActionCommand("ia");
        inventoryAvailable.addActionListener(this);

        productSellingPrice = new JTextField("Enter selling price");
        productSellingPrice.setActionCommand("sp");
        productSellingPrice.addActionListener(this);

        productCostPrice = new JTextField("Enter cost price");
        productCostPrice.setActionCommand("cp");
        productCostPrice.addActionListener(this);

        //Remove Product
        removeProduct = new JButton("Remove Product");
        removeProduct.setActionCommand("remove");
        removeProduct.addActionListener(this);

        removeProductName = new JTextField("Enter Product Name");
        removeProductName.setActionCommand("rs");
        removeProductName.addActionListener(this);
        removeTextField = removeProductName.getText();

        //search Product
        search = new JButton("Search");
        search.setActionCommand("search");
        search.addActionListener(this);

        searchField = new JTextField("Enter Product Name");
        searchField.setActionCommand("search field");
        searchField.addActionListener(this);

        searchResult = new JTextArea();

        //add sale
        addSale = new JButton("Add a new Sale");
        addSale.setActionCommand("add sale");
        addSale.addActionListener(this);

        saleProductName = new JTextField("Enter Product Name");
        saleProductName.setActionCommand("spn");
        saleProductName.addActionListener(this);

        quantity = new JTextField("Enter Quantity");
        quantity.setActionCommand("quantity");
        quantity.addActionListener(this);

        //Revreport
        revReport = new JButton("Generate Revenue Report");
        revReport.setActionCommand("revreport");
        revReport.addActionListener(this);

        revenue = new JTextField("Revenue");
        revenue.setActionCommand("value");
        revenue.addActionListener(this);

        grossProfit = new JTextField("Total Gross Profit");
        grossProfit.setActionCommand("gross");
        grossProfit.addActionListener(this);

        //JTable
        String[] columnNames = {"Product Name",
                "Inventory Available",
                "Selling Price",
                "Cost Price"};
        Object [] [] data = {
                {"Car",12,32,44},
                {"Bike",300,50,100}
        };

        displayFrame = new JList<>(listModel);
        displayFrame.setSelectionMode((ListSelectionModel.SINGLE_SELECTION));
        displayFrame.setSelectedIndex(0);
//        displayFrame.addListSelectionListener((ListSelectionListener) this);

        scrollPane = new JScrollPane(displayFrame);
        scrollPane.createVerticalScrollBar();


        MainWindowPanel.add(loadList).setBounds(735, 25, 150, 75);
        MainWindowPanel.add(savelist).setBounds(560, 25, 150, 75);

        MainWindowPanel.add(image).setBounds(300,0,200,200);

        MainWindowPanel.add(productName).setBounds(25, 25, 250, 30);
        MainWindowPanel.add(inventoryAvailable).setBounds(25, 55, 250, 30);
        MainWindowPanel.add(productCostPrice).setBounds(25, 85, 250, 30);
        MainWindowPanel.add(productSellingPrice).setBounds(25, 115, 250, 30);
        MainWindowPanel.add(addProduct).setBounds(25, 150, 200, 50);

        MainWindowPanel.add(removeProductName).setBounds(25, 200, 250, 30);
        MainWindowPanel.add(removeProduct).setBounds(25, 235, 200, 50);

        MainWindowPanel.add(search).setBounds(560, 110, 100, 30);
        MainWindowPanel.add(searchField).setBounds(660, 110, 220, 30);
        MainWindowPanel.add(searchResult).setBounds(560, 145, 320,50);

        MainWindowPanel.add(saleProductName).setBounds(25, 285, 250, 30);
        MainWindowPanel.add(quantity).setBounds(25, 315, 250, 30);
        MainWindowPanel.add(addSale).setBounds(25, 345, 200, 50);

        MainWindowPanel.add(revReport).setBounds(25, 395, 200, 50);
        MainWindowPanel.add(revenue).setBounds(25, 445, 250, 30);
        MainWindowPanel.add(grossProfit).setBounds(25, 475, 250, 30);

        MainWindowPanel.add(scrollPane).setBounds(300, 220, 570, 300);

        MainWindowPanel.setVisible(true);

    }

    //EFFECTS: Adds appropriate reactions to actions performed
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Load List")) {
            try {
                inventoryList = jsonReader.read();
                for (int x = 0; x < inventoryList.listSize(); x++) {
                    listModel.addElement(inventoryList.getList().get(x).toString());
                }
                System.out.println("Loaded the inventory list from " + JSON_STORE);
            } catch (IOException i) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }

        if (e.getActionCommand().equals("save")) {
            try {
                jsonWriter.open();
                jsonWriter.write(inventoryList);
                jsonWriter.close();
                System.out.println("Saved inventory list to " + JSON_STORE);
            } catch (FileNotFoundException f) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
        if (e.getActionCommand().equals("add")) {
            String addProductName = productName.getText();

            String inventoryString = inventoryAvailable.getText();
            int inventoryAvailableInt = Integer.parseInt(inventoryString);

            String sellingString = productSellingPrice.getText();
            int sellingInt = Integer.parseInt(sellingString);

            String costString = productCostPrice.getText();
            int costInt = Integer.parseInt(costString);

            productName.setText("Enter Product Name");
            inventoryAvailable.setText("Enter Inventory Available");
            productSellingPrice.setText("Enter Selling Price");
            productCostPrice.setText("Enter Cost price");
            product = new Product(addProductName, inventoryAvailableInt, sellingInt, costInt);
            inventoryList.addProduct(product);
            listModel.addElement(addProductName);
        }

        if (e.getActionCommand().equals("remove")) {
            inventoryList.removeProduct(removeProductName.getText());
            listModel.removeElement(removeProductName.getText());
            removeProductName.setText("Enter Product Name");

        }

        if (e.getActionCommand().equals("add sale")) {
            String quantityString = quantity.getText();
            int quantityInt = Integer.parseInt(quantityString);

            String saleProductNameString = saleProductName.getText();

            sale1 = new Sales(quantityInt, inventoryList.searchProduct(saleProductNameString));
            salesList.addSale(sale1);

        }

        if (e.getActionCommand().equals("revreport")) {
            salesList.revenueReport();

            int revenueInt = salesList.getRevenue();
            String revenueString = String.valueOf(revenueInt);

            int grossProfitInt = salesList.getTotalGrossProfit();
            String grossProfitString = String.valueOf(grossProfitInt);
            revenue.setText(revenueString);
            grossProfit.setText(grossProfitString);
        }

        if (e.getActionCommand().equals("search")) {
            inventoryList.searchProduct(searchField.getText());
            searchResult.setText((" Name:" + inventoryList.searchProduct(searchField.getText()).getProductName()
                    + "    Inventory available:"
                    + inventoryList.searchProduct(searchField.getText()).getInventoryAvailable()
                    + " \n Selling Price:" + inventoryList.searchProduct(searchField.getText()).getSellingPrice()
                    + "     Cost Price:" + inventoryList.searchProduct(searchField.getText()).getCostPrice()
                    + " \n Gross Profit:" + inventoryList.searchProduct(searchField.getText()).getGrossMargin()));

        }
    }



}

