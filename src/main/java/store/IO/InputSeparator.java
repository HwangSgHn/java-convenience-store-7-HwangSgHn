package store.IO;

import java.util.ArrayList;
import java.util.List;
import store.Purchase;

public class InputSeparator {
    public static List<Purchase> separateProductNamesAndQuantities(String purchaseProductNamesWithQuantity) {
        String[] productNamesWithQuantity = purchaseProductNamesWithQuantity.split(",");
        List<store.Purchase> purchases = new ArrayList<>();
        for (String productNameWithQuantity : productNamesWithQuantity) {
            productNameWithQuantity = productNameWithQuantity.substring(1, productNameWithQuantity.length() - 1);
            String[] productNameAndQuantity = productNameWithQuantity.split("-");
            String productName = productNameAndQuantity[0];
            int quantity = Integer.parseInt(productNameAndQuantity[1]);
            purchases.add(new Purchase(productName, quantity));
        }
        return purchases;
    }
}
