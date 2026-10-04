package converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TempRecordDAOTest {

    private final TempRecordDAO dao = new TempRecordDAO();
    private final TemperatureUnitDAO unitDao = new TemperatureUnitDAO();

    @Test
    void saveFindAndDelete() throws Exception {
        TemperatureUnit unit = unitDao.findBySymbol("C");
        int id = dao.save(new TempRecord(unit.getId(), 100.0, 212.0));
        assertTrue(id > 0);

        assertTrue(dao.findAll().stream()
                .anyMatch(r -> r.getId() == id && r.getResultValue() == 212.0));

        dao.delete(id);
        assertFalse(dao.findAll().stream().anyMatch(r -> r.getId() == id));
    }
}