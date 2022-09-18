package com.example.postgresoncloudsql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.dao.KenkoRecord;

@Service
public class JdbcService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final String table = "kenko_record";

  // データの一覧を返す
  public List<KenkoRecord> findAll() {
    // 実行する SQL を組み立てて実行
    String query = "SELECT * from " + table;
    List<KenkoRecord> records = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(KenkoRecord.class));
    return records;
  }

  // Create
  public String create(Long id, String name, String food) {
    String query = "INSERT INTO " + table + "(id, name, food) values (?, ?, ?)";
    Object[] params = { id, name, food };

    int result = jdbcTemplate.update(query, params);
    return String.format("SUCCESS(result: %d)", result);
  }

  // Read
  public List<KenkoRecord> read(Long id) {
    String query = "SELECT * from " + table + " WHERE id = ?";
    Object params[] = { String.valueOf(id) };

    List<KenkoRecord> records = jdbcTemplate.query(
        query,
        new BeanPropertyRowMapper<>(KenkoRecord.class),
        params);
    return records;
  }

  // Update
  public String update(Long id, String name, String food) {
    String query = "UPDATE " + table + " SET name = ?, food = ? WHERE id = ?";
    Object params[] = { name, food, String.valueOf(id)};

    int result = jdbcTemplate.update(query, params);
    return String.format("SUCCESS(result: %d)", result);
  }

  // Delete
  public String delete(Long id) {
    String query = "DELETE FROM " + table + " WHERE id = ?";
    Object params[] = { String.valueOf(id) };

    int result = jdbcTemplate.update(query, params);
    return String.format("SUCCESS(result: %d)", result);
  }
}
