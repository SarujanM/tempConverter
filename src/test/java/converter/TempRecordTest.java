package converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TempRecordTest {

    @Test
    void gettersReturnValues() {
        TempRecord record = new TempRecord(5, 2, 100.0, 37.7);
        assertEquals(5, record.getId());
        assertEquals(2, record.getUnitId());
        assertEquals(100.0, record.getInputValue());
        assertEquals(37.7, record.getResultValue());
    }

    @Test
    void shortConstructorSetsIdToZero() {
        TempRecord record = new TempRecord(2, 10.0, 50.0);
        assertEquals(0, record.getId());
        assertEquals("#0: 10.0 -> 50.0", record.toString());
    }
}