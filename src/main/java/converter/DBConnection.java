package converter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    static String env(String key, String fallback) {
        String value = System.getenv(key);
        return (value == null || value.isBlank()) ? fallback : value;
    }

    public static String url() {
        return "jdbc:mariadb://" + env("DB_HOST", "localhost") + ":"
                + env("DB_PORT", "3306") + "/" + env("DB_NAME", "tempconverter");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                url(), env("DB_USER", "temp"), env("DB_PASSWORD", "temp1234"));
    }
}
