package com.example.mysqlonazure.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mysqlonazure.dto.EmployeeSalaryDTO;
import com.example.mysqlonazure.mapper.EmployeeSalaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 従業員に関するサービスクラス
@Service
public class EmployeeService {
  private final EmployeeSalaryMapper employeeSalaryMapper;

  // コンストラクタインジェクション
  public EmployeeService(EmployeeSalaryMapper employeeSalaryMapper) {
    this.employeeSalaryMapper = employeeSalaryMapper;
  }

  // 給与取得上位N件の取得
  public List<EmployeeSalaryDTO> topOfSalaryEmployee(String year, String month, int limit) {
    // 月初日と月末日を取得
    Date startDate = Date.valueOf(year + "-" + month + "-01");
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(startDate);
    calendar.add(Calendar.MONTH, 1);

    Date endDate = new Date(calendar.getTimeInMillis());
    
    // ロガーを使用し、引数のログ出力を行う
    Logger log = LoggerFactory.getLogger(EmployeeService.class);
    log.info("topOfSalaryEmployee() startDate:" + startDate + " endDate:" + endDate + " limit:" + limit);

    return employeeSalaryMapper.topOfSalaryEmployee(startDate, endDate, limit);
  }

}
