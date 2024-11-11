package store.IO;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import store.Product;
import store.Promotion;

public class InputFile {
    private static final String path = System.getProperty("user.dir") + "/src/main/resources/";

    public static List<Product> readProducts() {
        BufferedReader bufferedReader;
        List<Product> products = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(path + "products.md"));
            bufferedReader.lines().skip(1).forEach(line -> {
                String[] productInfo = line.split(",");
                String name = productInfo[0];
                int price = Integer.parseInt(productInfo[1]);
                int quantity = Integer.parseInt(productInfo[2]);
                String promotionName = productInfo[3];
                Product product = new Product(name, price, quantity, promotionName);
                if ("null".equals(promotionName)) {
                    product = new Product(name, price, quantity);
                }
                products.add(product);
            });
        } catch (IOException ioException) {
            System.out.println("products.md를 찾을 수 없습니다.");
        }
        return products;
    }

    public static List<Promotion> readPromotions() {
        BufferedReader bufferedReader;
        List<Promotion> promotions = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(path + "promotions.md"));
            bufferedReader.lines().skip(1).forEach(line -> {
                String[] promotionInfo = line.split(",");
                String name = promotionInfo[0];
                int buy = Integer.parseInt(promotionInfo[1]);
                int get = Integer.parseInt(promotionInfo[2]);
                LocalDate start_date = LocalDate.parse(promotionInfo[3]);
                LocalDate end_date = LocalDate.parse(promotionInfo[4]);
                promotions.add(new Promotion(name, buy, get, start_date, end_date));
            });
        } catch (IOException ioException) {
            System.out.println("promotions.md를 찾을 수 없습니다.");
        }
        return promotions;
    }
}
