package store.IO;

import java.util.List;
import store.StockManager;
import store.Purchase;

public class InputValidator {
    public static boolean format(String input) {
        return input.matches("\\[[가-힣]+-\\d+](,\\[[가-힣]+-\\d+])*");
    }

    public static boolean purchases(StockManager stockManager, List<Purchase> purchases) {
        for (Purchase purchase : purchases) {
            String name = purchase.getName();
            int quantity = purchase.getQuantity();
            if (!stockManager.canPurchase(name, quantity)) {
                return false;
            }
        }
        return true;
    }
}
