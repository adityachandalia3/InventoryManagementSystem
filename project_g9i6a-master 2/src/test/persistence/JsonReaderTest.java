package persistence;

import model.Inventory;
import model.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Inventory i = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyInventoryList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInventoryList.json");
        try {
            Inventory i  = reader.read();
            assertEquals(0, i.listSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralInventoryList() {
        Inventory i = new Inventory();
        i.addProduct(new Product("Rackets", 200, 20, 10));
        i.addProduct(new Product("Cricket Bats", 120, 200, 100));
        JsonReader reader = new JsonReader("./data/testReaderGeneralInventoryList.json");


        try {
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralInventoryList.json");
            writer.open();
            writer.write(i);
            writer.close();
            i = reader.read();
            List<Product> products = i.getProducts();
            assertEquals(2, i.getProducts().size());
            checkProduct("Rackets", 200, 20, 10, products.get(0));
            checkProduct("Cricket Bats", 120,200, 100,products.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
