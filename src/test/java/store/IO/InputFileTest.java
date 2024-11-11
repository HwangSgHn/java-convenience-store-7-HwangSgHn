package store.IO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import store.Product;

class InputFileTest {
    static List<Product> products;

    @BeforeAll
    static void setUp() {
        products = InputFile.readProducts();
    }

    @ParameterizedTest
    @CsvSource(value = {"0,10", "2,8", "3,7", "14,1", "15,10"})
    void readProductsTest(int index, int expected) {
        Product product = products.get(index);

        assertEquals(expected, product.getQuantity());
    }
}