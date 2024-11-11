package store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LocalDateTest {
    @ParameterizedTest
    @CsvSource(value = {
            "2021,5,31,false",
            "2021,6,1,true",
            "2021,9,15,true",
            "2021,12,31,true",
            "2022,1,1,false"
    })
    public void localDateTest(int year, int month, int day, boolean expected) {
        LocalDate now = LocalDate.of(year, month, day);
        LocalDate start_date = LocalDate.of(2021, 6, 1);
        LocalDate end_date = LocalDate.of(2021, 12, 31);
        assertEquals(expected, !(now.isBefore(start_date) || now.isAfter(end_date)));
    }
}
