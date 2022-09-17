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

  // データの一覧を返す
  public List<KenkoRecord> findAll() {
    // 実行する SQL を組み立てて実行
    String query = "SELECT * from kenko_record";
    List<KenkoRecord> records = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(KenkoRecord.class));
    return records;
  }  
}
