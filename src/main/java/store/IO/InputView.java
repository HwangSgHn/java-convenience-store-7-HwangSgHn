package store.IO;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import store.StockManager;
import store.Purchase;

public class InputView {
    private static final String YES = "Y";

    public List<Purchase> readPurchaseProductNamesWithQuantity(StockManager stockManager) {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String purchaseProductNamesWithQuantity = readValidInput();
        List<Purchase> purchases = InputSeparator.separateProductNamesAndQuantities(purchaseProductNamesWithQuantity);
        while (!stockManager.canPurchases(purchases)) {
            purchaseProductNamesWithQuantity = readValidInput();
            purchases = InputSeparator.separateProductNamesAndQuantities(purchaseProductNamesWithQuantity);
        }
        return purchases;
    }

    private String readValidInput() {
        String input = Console.readLine();
        while (!InputValidator.format(input)) {
            try {
                throw new IllegalArgumentException("올바르지 않은 형식으로 입력했습니다.");
            } catch (IllegalArgumentException illegalArgumentException) {
                OutputView.printIllegalArgumentException(illegalArgumentException.getMessage());
                input = Console.readLine();
            }
        }
        return input;
    }

    public boolean readPromotionAddition(String productName, int quantity) {
        System.out.printf("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n", productName, quantity);
        String promotionAddition = Console.readLine();
        return YES.equals(promotionAddition);
    }

    public boolean readNoPromotionApply(String productName, int quantity) {
        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n", productName, quantity);
        String noPromotionApply = Console.readLine();
        return YES.equals(noPromotionApply);
    }

    public boolean readMembership() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        String membership = Console.readLine();
        return YES.equals(membership);
    }

    public boolean readContinue() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String continuePurchasing = Console.readLine();
        return YES.equals(continuePurchasing);
    }
}
