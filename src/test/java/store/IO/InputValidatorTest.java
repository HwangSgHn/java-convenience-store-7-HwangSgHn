package store.IO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InputValidatorTest {
    @ParameterizedTest
    @CsvSource(value = {
            "[탄산수-5]:true",
            "[탄산수-5],[오렌지주스-9]:true",
            "탄산수-5:false",
            "[탄산수]:false",
            "[탄산수-5],:false"
    }, delimiter = ':')
    void format(String input, boolean expected) {
        assertEquals(expected, InputValidator.format(input));
    }
}