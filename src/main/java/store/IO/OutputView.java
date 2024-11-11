package store.IO;

import java.text.DecimalFormat;
import java.util.List;
import store.BuyCount;
import store.Product;

public class OutputView {
    private static final DecimalFormat formatter = new DecimalFormat("###,###");
    private static final String ERROR = "[ERROR] ";
    private static final String RE_INPUT = " 다시 입력해 주세요.";

    public void printProductsWithGreeting(List<Product> products) {
        StringBuilder productsWithGreeting = new StringBuilder("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n\n");
        for (Product product : products) {
            productsWithGreeting.append("- ").append(product.toString()).append('\n');
        }
        System.out.println(productsWithGreeting);
    }

    public static void printIllegalArgumentException(String message) {
        System.out.println(ERROR + message + RE_INPUT);
    }

    public void printReceipt(List<BuyCount> buyCounts, int totalCount, int total, int promotionDiscount, int membershipDiscount) {
        StringBuilder receipt = new StringBuilder("===========W 편의점=============\n");
        receipt.append("상품명        수량\t금액\n");
        for (BuyCount buyCount : buyCounts) {
            receipt.append(buyCount.getName()).append("\t\t").append(buyCount.getDiscountQuantity() + buyCount.getGeneralQuantity())
                    .append(" \t").append(formatter.format(buyCount.calculatePrice(buyCount.getDiscountQuantity() + buyCount.getGeneralQuantity()))).append("\n");
        }
        receipt.append("===========증\t정=============\n");
        for (BuyCount buyCount : buyCounts) {
            if (buyCount.getPresentation() != 0) {
                receipt.append(buyCount.getName()).append("\t\t").append(buyCount.getPresentation()).append("\n");
            }
        }
        receipt.append("==============================\n");
        receipt.append("총구매액\t\t").append(totalCount).append("\t").append(formatter.format(total)).append("\n")
                .append("행사할인\t\t\t-").append(formatter.format(promotionDiscount)).append("\n")
                .append("멤버십할인\t\t\t-").append(formatter.format(membershipDiscount)).append("\n")
                .append("내실돈\t\t\t").append(formatter.format(total - promotionDiscount - membershipDiscount));
        System.out.println(receipt);
    }
}