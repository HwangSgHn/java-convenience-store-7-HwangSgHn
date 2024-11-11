package store;

public class Application {
    public static void main(String[] args) {
        PaymentSystem paymentSystem = new PaymentSystem();
        boolean continuePurchase = true;
        while (continuePurchase) {
            paymentSystem.init();
            paymentSystem.inputProductsWithQuantity();
            paymentSystem.promotionAddition();
            paymentSystem.generalPurchase();
            paymentSystem.membership();
            paymentSystem.minusStock();
            paymentSystem.receipt();
            continuePurchase = paymentSystem.continuePurchase();
        }

    }
}
