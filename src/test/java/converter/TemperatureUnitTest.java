package converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureUnitTest {

    @Test
    void gettersReturnValues() {
        TemperatureUnit unit = new TemperatureUnit(1, "Celsius", "C");
        assertEquals(1, unit.getId());
        assertEquals("Celsius", unit.getName());
        assertEquals("C", unit.getSymbol());
        assertEquals("Celsius (C)", unit.toString());
    }
}