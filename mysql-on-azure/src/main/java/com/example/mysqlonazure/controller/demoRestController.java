package com.example.mysqlonazure.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.mysqlonazure.dto.DiaryDTO;
import com.example.mysqlonazure.dto.EmployeeSalaryDTO;
import com.example.mysqlonazure.service.DiaryService;
import com.example.mysqlonazure.service.EmployeeService;

// HTTP GETリクエストを受け取り、JSON形式の情報を返却するインタフェース
// この処理をベースにDBアクセス処理を作る
@RestController
public class demoRestController {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  DiaryService diaryService; // Instantiate the DiaryService class

  @Autowired
  EmployeeService employeeService; // Instantiate the EmployeeService class

  @GetMapping("/hello")
  private Map<String, String> hello() {
    return Map.of("msg", "こんにちは。");
  }

  // 1件取得
  @GetMapping("/jdbcselect/{id}")
  private List<DiaryDTO> jdbcselect(@PathVariable String id) {
    List<DiaryDTO> list = jdbcTemplate.query("SELECT * FROM diary WHERE id = ?",
        new BeanPropertyRowMapper<>(DiaryDTO.class), id);
    return list;
  }

  // 全件取得
  @GetMapping("/jdbcselect/")
  private List<DiaryDTO> jdbcselect() {
    List<DiaryDTO> list = jdbcTemplate.query("SELECT * FROM diary", new BeanPropertyRowMapper<>(DiaryDTO.class));
    return list;
  }

  // 全件取得(基本形)
  // サンプルとして残す。DTOクラスを使うことが望ましい
  @GetMapping("/jdbcselectbase/")
  private List<Map<String, Object>> jdbcselectbase() {
    List<Map<String, Object>> list;
    list = jdbcTemplate.queryForList("SELECT * FROM diary");
    return list;
  }

  // --------------------------------------------------
  // 以下、Serviceクラスを使ったDBアクセス処理

  // 1件取得
  @GetMapping("/diary/{id}")
  private DiaryDTO getDiary(@PathVariable String id) {
    DiaryDTO diary = diaryService.getDiaryById(id);
    return diary;
  }

  // 全件取得
  @GetMapping("/diary/")
  private List<DiaryDTO> getAllDiary() {
    List<DiaryDTO> diarys = diaryService.getAllDiaries();
    return diarys;
  }

  // 給与取得者上位N件の取得
  // year : 従業員の誕生年
  // month : 従業員の誕生月
  // limit : 取得件数
  @GetMapping("/employeesalary/{year}/{month}/{limit}")
  private List<EmployeeSalaryDTO> getSalaryRanking(@PathVariable String year, @PathVariable String month,
      @PathVariable int limit) {
    List<EmployeeSalaryDTO> employees = employeeService.topOfSalaryEmployee(year, month, limit);
    return employees;
  }

}
