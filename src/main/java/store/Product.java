package store;

import java.text.DecimalFormat;

public class Product {
    private static final DecimalFormat formatter = new DecimalFormat("###,###");
    private final String name;
    private final int price;
    private int quantity;
    private final String promotionName;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = "";
    }

    public Product(String name, int price, int quantity, String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        String quantityStr = quantity + "개";
        if ("0개".equals(quantityStr)) {
            quantityStr = "재고 없음";
        }
        return name + " " + formatter.format(price) + "원 " + quantityStr + " " + promotionName;
    }
}
