package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RevenueTest {
    private Revenue testList2;
    private Inventory testList1;
    private Sales testSale;
    private Product testProduct2;
    private Sales testSale2;

    @BeforeEach
    void beforeRun() {
        this.testList2 = new Revenue();
        this.testList1 = new Inventory();
        this.testProduct2 = new Product("Bottles", 500, 100, 50);
        this.testSale = new Sales(25, testProduct2);
        this.testSale2 = new Sales(450, testProduct2);
        testList1.addProduct(testProduct2);
    }

    @Test
    void testAddSales() {
        Assertions.assertEquals("Success! Sale was recorded", this.testList2.addSale(testSale));
        Assertions.assertEquals("Alert!" + this.testSale2.getProductName() + "has Low Inventory:"
                        + "25",
                this.testList2.addSale(testSale2));
        Assertions.assertEquals("Error! Sale could not be recorded", this.testList2.addSale(testSale));
    }

    @Test
    void testRevenueReports() {
        Assertions.assertEquals("Total Revenue:" + this.testList2.getRevenue()
                + "and Total Gross Profit:" + this.testList2.getTotalGrossProfit(), this.testList2.revenueReport());
    }
}
