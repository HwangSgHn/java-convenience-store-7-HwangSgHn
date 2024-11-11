package store;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import store.IO.InputFile;

class StockManagerTest {
    StockManager stockManager;

    @BeforeEach
    void setUp() {
        stockManager = new StockManager(InputFile.readProducts(), InputFile.readPromotions());
        stockManager.addNoStack();
    }

    @ParameterizedTest
    @CsvSource(value = {"콜랑,1,false", "콜라,100,false", "콜라,10,true"})
    void canPurchases(String name, int quantity, boolean expected) {
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(new Purchase(name, quantity));
        assertEquals(expected, stockManager.canPurchases(purchases));
    }

    @ParameterizedTest
    @CsvSource(value = {"콜라,12,10,2,3", "탄산수,2,2,0,0", "물,10,0,10,0"})
    void calculateBuyCount(String name, int quantity, int expectedDQ, int expectedGQ, int expectedP) {
        BuyCount buyCount = stockManager.calculateBuyCount(new Purchase(name, quantity));
        assertEquals(expectedDQ, buyCount.getDiscountQuantity());
        assertEquals(expectedGQ, buyCount.getGeneralQuantity());
        assertEquals(expectedP, buyCount.getPresentation());
    }
}