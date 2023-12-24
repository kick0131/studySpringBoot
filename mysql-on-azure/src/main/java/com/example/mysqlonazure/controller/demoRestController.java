package com.example.mysqlonazure.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// HTTP GETリクエストを受け取り、JSON形式の情報を返却するインタフェース
// この処理をベースにDBアクセス処理を作る
@RestController
public class demoRestController {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @GetMapping("/hello")
  private Map<String, String> hello() {
    return Map.of("msg", "こんにちは。");
  }

  @GetMapping("/jdbcselect/{id}")
  private List<Map<String, Object>> jdbcselect(@PathVariable String id) {
    List<Map<String, Object>> list;
    list = jdbcTemplate.queryForList("SELECT * FROM diary WHERE id = ?", id);
    return list;
  }

}
