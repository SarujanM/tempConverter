package converter;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DBConnectionTest {

    @Test
    void envReturnsFallbackWhenVariableMissing() {
        assertEquals("fallback", DBConnection.env("NO_SUCH_VARIABLE_XYZ", "fallback"));
    }

    @Test
    void urlStartsWithJdbcMariadb() {
        assertTrue(DBConnection.url().startsWith("jdbc:mariadb://"));
    }

    @Test
    void connectionOpens() throws Exception {
        try (Connection connection = DBConnection.getConnection()) {
            assertTrue(connection.isValid(2));
        }
    }
}