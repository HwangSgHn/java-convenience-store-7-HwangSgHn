package store;

import camp.nextstep.edu.missionutils.DateTimes;
import java.util.List;
import store.IO.OutputView;

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

    public boolean canPurchase(String name, int quantity) {
        List<Product> productsFindByName = products.stream().filter(product -> name.equals(product.getName())).toList();
        if (productsFindByName.isEmpty()) {
            try {
                throw new IllegalArgumentException("존재하지 않는 상품입니다.");
            } catch (IllegalArgumentException illegalArgumentException) {
                OutputView.printIllegalArgumentException(illegalArgumentException.getMessage());
                return false;
            }
        }
        int stockQuantity = productsFindByName.stream().mapToInt(Product::getQuantity).sum();
        if (stockQuantity < quantity) {
            try {
                throw new IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다.");
            } catch (IllegalArgumentException illegalArgumentException) {
                OutputView.printIllegalArgumentException(illegalArgumentException.getMessage());
                return false;
            }
        }
        return true;
    }

    public BuyCount calculateBuyCount(Purchase purchase) {
        List<Product> purchaseProducts = products.stream().filter(product -> product.getName().equals(purchase.getName())).toList();
        if (purchaseProducts.size() == 1) {
            return new BuyCount(0, purchase.getQuantity(), 0, 0, purchase.getName(), purchaseProducts.getFirst().getPrice(), false);
        }
        Product promotionProduct = purchaseProducts.getFirst();
        Promotion promotion = findPromotion(promotionProduct);
        if (!promotion.isWithinDeadline(DateTimes.now())) {
            if (promotionProduct.getQuantity() >= purchase.getQuantity()) {
                return new BuyCount(purchase.getQuantity(), 0, 0, promotion.getSumOfBuyAndGet(), purchase.getName(), promotionProduct.getPrice(), false);
            }
            int productQuantity = promotionProduct.getQuantity();
            return new BuyCount(productQuantity, purchase.getQuantity() - productQuantity, 0, promotion.getSumOfBuyAndGet(), purchase.getName(), promotionProduct.getPrice(), false);
        }
        if (promotionProduct.getQuantity() > purchase.getQuantity()) {
            int discountQuantity = promotion.discountQuantity(purchase.getQuantity());
            return new BuyCount(purchase.getQuantity(), 0, discountQuantity, promotion.getSumOfBuyAndGet(), purchase.getName(), promotionProduct.getPrice(), promotion.canGetOneMore(purchase.getQuantity(), discountQuantity));
        } else if (promotionProduct.getQuantity() == purchase.getQuantity()) {
            int discountQuantity = promotion.discountQuantity(purchase.getQuantity());
            return new BuyCount(purchase.getQuantity(), 0, discountQuantity, promotion.getSumOfBuyAndGet(), purchase.getName(), promotionProduct.getPrice(), false);
        }
        int discountQuantity = promotion.discountQuantity(promotionProduct.getQuantity());
        int productQuantity = promotionProduct.getQuantity();
        return new BuyCount(productQuantity, purchase.getQuantity() - productQuantity, discountQuantity, promotion.getSumOfBuyAndGet(), purchase.getName(), promotionProduct.getPrice(), false);
    }

    private Promotion findPromotion(Product product) {
        return promotions.stream().filter(pm -> pm.getName().equals(product.getPromotionName())).findFirst().get();
    }
}
