package model;

public class Sales {


    private int quantity;
    private Product productName;

    public Sales(int quantity, Product productName) {
        this.productName = productName;
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public Product getProductName() {
        return productName;
    }
}
