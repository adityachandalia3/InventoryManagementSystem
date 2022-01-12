package model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InventoryTest {
    private Inventory testList;
    private Inventory tempList;
    private Product testProduct;


    @BeforeEach
    void beforeRun() {
        this.testList = new Inventory();
        this.testProduct = new Product("Pens", 500, 250, 100);
        this.tempList = new Inventory();
        tempList.addProduct(testProduct);
    }

    @Test
    void testAddProduct() {
        Assertions.assertEquals("Your product was added",
                this.testList.addProduct(testProduct));
    }

    @Test
    void testRemoveProduct() {
        Assertions.assertEquals("The item was removed!", this.testList.removeProduct(testProduct.toString()));
    }

    @Test
    void testSearch() {
        Assertions.assertEquals(this.testProduct, tempList.searchProduct(this.testProduct.toString()));
        Assertions.assertNull(tempList.searchProduct("l;asdjfkladlk;jfsak;jadfs;jkl;sd"));
    }

    @Test
    void testListSize(){
        Inventory tempList1 = new Inventory();
        Assertions.assertEquals(0,tempList1.listSize());
        tempList1.addProduct(testProduct);
        Assertions.assertEquals(1, tempList1.listSize());
    }



}

