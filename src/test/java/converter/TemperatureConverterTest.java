package converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemperatureConverterTest {

    private TemperatureConverter converter;

    @BeforeEach
    void setUp() {
        converter = new TemperatureConverter();
    }

    @Test
    void fahrenheitToCelsiusFreezingPoint() {
        assertEquals(0.0, converter.fahrenheitToCelsius(32), 0.001);
    }

    @Test
    void fahrenheitToCelsiusBoilingPoint() {
        assertEquals(100.0, converter.fahrenheitToCelsius(212), 0.001);
    }

    @Test
    void celsiusToFahrenheitFreezingPoint() {
        assertEquals(32.0, converter.celsiusToFahrenheit(0), 0.001);
    }

    @Test
    void celsiusToFahrenheitBoilingPoint() {
        assertEquals(212.0, converter.celsiusToFahrenheit(100), 0.001);
    }

    @Test
    void isExtremeTemperatureBelowMinus40() {
        assertTrue(converter.isExtremeTemperature(-50));
    }

    @Test
    void isExtremeTemperatureAboveFifty() {
        assertTrue(converter.isExtremeTemperature(60));
    }
    @Test
    void isExtremeTemperatureNormalValue() {
        assertFalse(converter.isExtremeTemperature(20));
    }

}