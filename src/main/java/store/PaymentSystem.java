package store;

import java.util.ArrayList;
import java.util.List;
import store.IO.InputFile;
import store.IO.InputView;
import store.IO.OutputView;

public class PaymentSystem {
    private final StockManager stockManager;
    private final InputView inputView;
    private final OutputView outputView;
    private List<BuyCount> buyCounts;
    private List<Purchase> purchases;
    private boolean membership;

    public PaymentSystem() {
        stockManager = new StockManager(InputFile.readProducts(), InputFile.readPromotions());
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void init() {
        outputView.printProductsWithGreeting(stockManager.getProducts());
        buyCounts = new ArrayList<>();
    }

    public void inputProductsWithQuantity() {
        purchases = inputView.readPurchaseProductNamesWithQuantity(stockManager);
    }

    public void promotionAddition() {
        for (Purchase purchase : purchases) {
            BuyCount buyCount = stockManager.calculateBuyCount(purchase);
            if (buyCount.isPromotion()) {
                if (inputView.readPromotionAddition(purchase.getName(), 1)) {
                    buyCount.plusPresentation();
                }
            }
            buyCounts.add(buyCount);
        }
    }

    public void generalPurchase() {
        for (BuyCount buyCount : buyCounts) {
            if (buyCount.getDiscountQuantity() == 0 || buyCount.getGeneralQuantity() == 0) continue;
            int noPromotion = buyCount.getGeneralQuantity()
                    + (buyCount.getDiscountQuantity() - buyCount.getPresentation() * buyCount.getSumOfBuyAndGet());
            if (!inputView.readNoPromotionApply(buyCount.getName(), noPromotion)) {
                buyCount.minusNoPromotionQuantity(noPromotion, buyCount.getGeneralQuantity());
            }
        }
    }

    public void membership() {
        membership = inputView.readMembership();
    }

    public void minusStock() {
        for (BuyCount buyCount : buyCounts) {
            stockManager.minusProductStock(buyCount.getName(), buyCount.getDiscountQuantity(), buyCount.getGeneralQuantity());
        }
    }

    public void receipt() {
        int totalCount = 0;
        int total = 0;
        int promotionDiscount = 0;
        int membershipDiscount = 0;
        for (BuyCount buyCount : buyCounts) {
            totalCount += (buyCount.getDiscountQuantity() + buyCount.getGeneralQuantity());
            total += (buyCount.getDiscountQuantity() + buyCount.getGeneralQuantity()) * buyCount.getPrice();
            if (buyCount.getPresentation() != 0) {
                promotionDiscount += buyCount.getPresentation() * buyCount.getPrice();
            }
            if (membership && buyCount.getPresentation() == 0) {
                membershipDiscount += (buyCount.getDiscountQuantity() + buyCount.getGeneralQuantity()) * buyCount.getPrice();
            }
        }
        membershipDiscount = membershipDiscount / 10 * 3;
        outputView.printReceipt(buyCounts, totalCount, total, promotionDiscount, membershipDiscount);
    }

    public boolean continuePurchase() {
        return inputView.readContinue();
    }
}
