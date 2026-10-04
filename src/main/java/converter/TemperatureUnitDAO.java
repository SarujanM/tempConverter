package converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TemperatureUnitDAO {

    public List<TemperatureUnit> findAll() throws SQLException {
        List<TemperatureUnit> units = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT id, name, symbol FROM temperature_unit ORDER BY id")) {
            while (rs.next()) {
                units.add(new TemperatureUnit(rs.getInt("id"), rs.getString("name"), rs.getString("symbol")));
            }
        }
        return units;
    }

    public TemperatureUnit findBySymbol(String symbol) throws SQLException {
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "SELECT id, name, symbol FROM temperature_unit WHERE symbol = ?")) {
            ps.setString(1, symbol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TemperatureUnit(rs.getInt("id"), rs.getString("name"), rs.getString("symbol"));
                }
            }
        }
        return null;
    }
}