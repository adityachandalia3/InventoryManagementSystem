package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {
    private Product testProduct3;

    @BeforeEach
    void runBefore() {
        this.testProduct3 = new Product("Car", 50, 50, 30);
    }

    @Test
    void testGetPrice() {
        Assertions.assertEquals(30, this.testProduct3.getCostPrice());
    }
}



