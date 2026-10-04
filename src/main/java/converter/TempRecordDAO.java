package converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TempRecordDAO {

    public int save(TempRecord record) throws SQLException {
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO temp_record (unit_id, input_value, result_value) VALUES (?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, record.getUnitId());
            ps.setDouble(2, record.getInputValue());
            ps.setDouble(3, record.getResultValue());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    public List<TempRecord> findAll() throws SQLException {
        List<TempRecord> records = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(
                     "SELECT id, unit_id, input_value, result_value FROM temp_record ORDER BY id DESC")) {
            while (rs.next()) {
                records.add(new TempRecord(rs.getInt("id"), rs.getInt("unit_id"),
                        rs.getDouble("input_value"), rs.getDouble("result_value")));
            }
        }
        return records;
    }

    public void delete(int id) throws SQLException {
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM temp_record WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}