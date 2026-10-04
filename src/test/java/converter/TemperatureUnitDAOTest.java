package converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemperatureUnitDAOTest {

    private final TemperatureUnitDAO dao = new TemperatureUnitDAO();

    @Test
    void findAllReturnsAtLeastThreeUnits() throws Exception {
        assertTrue(dao.findAll().size() >= 3);
    }

    @Test
    void findBySymbolFindsCelsius() throws Exception {
        TemperatureUnit unit = dao.findBySymbol("C");
        assertNotNull(unit);
        assertEquals("Celsius", unit.getName());
    }

    @Test
    void findBySymbolReturnsNullForUnknown() throws Exception {
        assertNull(dao.findBySymbol("X"));
    }
}