package store;

import java.util.List;

public class StockManager {
    private final List<Product> products;
    private final List<Promotion> promotions;

    public StockManager(List<Product> products, List<Promotion> promotions) {
        this.products = products;
        this.promotions = promotions;
    }

    public List<Product> getProducts() {
        return products;
    }
}
