package persistence;

import model.Inventory;
import model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProduct(String productName, int inventoryAvailable, int sellingPrice, int costPrice,
                                Product product) {
        assertEquals(productName, product.getProductName());
        assertEquals(inventoryAvailable, product.getInventoryAvailable());
        assertEquals(sellingPrice, product.getSellingPrice());
        assertEquals(costPrice, product.getCostPrice());
        assertEquals(sellingPrice, product.getSellingPrice());
    }
}
