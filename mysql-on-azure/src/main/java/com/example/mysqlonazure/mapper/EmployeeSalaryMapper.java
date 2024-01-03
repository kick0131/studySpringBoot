package com.example.mysqlonazure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mysqlonazure.dto.EmployeeSalaryDTO;
import java.util.Date;

// 以下のSQLを参考にMapperクラスを作成する
// BETWEEN,LIMIT内の値は、引数で受け取ることとする
//
// select e.emp_no,sum(s.salary) as sums,e.birth_date,e.first_name,e.last_name, e.gender,e.hire_date  from employees as e
//  natural inner join salaries as s 
//  WHERE e.birth_date BETWEEN '1953-01-01' AND '1953-01-31'
//  group by e.emp_no
//  order by sums desc
//  limit 10;
//
// MyBatisの動作確認
// EmployeeSalaryMapper.xmlで定義されたidとメソッド名で紐づけを行い、
// SQLの実行とマッピング結果の返却を行う
@Mapper
public interface EmployeeSalaryMapper {

    // 上位N名の誕生年月別の従業員情報を取得
    List<EmployeeSalaryDTO> topOfSalaryEmployee(Date startDate, Date endDate, int limit);

}
