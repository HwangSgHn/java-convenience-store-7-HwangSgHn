package store;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Promotion {
    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate start_date;
    private final LocalDate end_date;

    public Promotion(String name, int buy, int get, LocalDate start_date, LocalDate end_date) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getName() {
        return name;
    }

    public int getSumOfBuyAndGet() {
        return buy + get;
    }

    public boolean isWithinDeadline(LocalDateTime now) {
        LocalDate nowLocalDate = now.toLocalDate();
        return !(nowLocalDate.isBefore(start_date) || nowLocalDate.isAfter(end_date));
    }

    public int discountQuantity(int quantity) {
        return quantity / (buy + get);
    }

    public boolean canGetOneMore(int quantity, int discountQuantity) {
        return quantity - discountQuantity * (buy + get) == buy;
    }
}
