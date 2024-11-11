package store;

public class BuyCount {
    private int discountQuantity;
    private int generalQuantity;
    private int presentation;
    private final int sumOfBuyAndGet;
    private final String name;
    private final int price;
    private final boolean promotion;

    public BuyCount(int discountQuantity, int generalQuantity, int presentation, int sumOfBuyAndGet, String name, int price, boolean promotion) {
        this.discountQuantity = discountQuantity;
        this.generalQuantity = generalQuantity;
        this.presentation = presentation;
        this.sumOfBuyAndGet = sumOfBuyAndGet;
        this.name = name;
        this.price = price;
        this.promotion = promotion;
    }

    public int getDiscountQuantity() {
        return discountQuantity;
    }

    public int getGeneralQuantity() {
        return generalQuantity;
    }

    public int getPresentation() {
        return presentation;
    }

    public int getSumOfBuyAndGet() {
        return sumOfBuyAndGet;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public int calculatePrice(int quantity) {
        return price * quantity;
    }

    public void plusPresentation() {
        this.discountQuantity++;
        this.presentation++;
    }

    public void minusNoPromotionQuantity(int noPromotion, int generalQuantity) {
        discountQuantity -= noPromotion - generalQuantity;
        this.generalQuantity = 0;
    }
}
