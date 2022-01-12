package persistence;

import model.Inventory;
import model.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Inventory i = new Inventory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Inventory i = new Inventory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInventoryList.json");
            writer.open();
            writer.write(i);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyInventoryList.json");
            i = reader.read();
            assertEquals(0, i.listSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Inventory i = new Inventory();
            i.addProduct(new Product("Rackets", 200, 20, 10));
            i.addProduct(new Product("Cricket Bats", 120, 200, 100));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralInventoryList.json");
            writer.open();
            writer.write(i);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralInventoryList.json");
            i = reader.read();
            List<Product> products = i.getProducts();
            assertEquals(2, i.getProducts().size());
            checkProduct("Rackets", 200, 20, 10, products.get(0));
            checkProduct("Cricket Bats", 120, 200, 100,
                    products.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

