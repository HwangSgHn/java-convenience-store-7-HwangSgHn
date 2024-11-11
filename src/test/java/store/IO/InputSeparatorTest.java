package store.IO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import store.Purchase;

class InputSeparatorTest {

    @ParameterizedTest
    @CsvSource(value = {"[초코바-10]:초코바:10", "[사이다-15],[컵라면-11]:사이다,컵라면:15,11"}, delimiter = ':')
    void separateProductNamesAndQuantities(String productNamesAndQuantities, String name, String quantity) {
        List<Purchase> purchases = InputSeparator.separateProductNamesAndQuantities(productNamesAndQuantities);
        String[] names = name.split(",");
        int[] quantities = Stream.of(quantity.split(",")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < purchases.size(); i++) {
            assertEquals(names[i], purchases.get(i).getName());
            assertEquals(quantities[i], purchases.get(i).getQuantity());
        }
    }
}