package com.example.mysqlonazure.dto;

import java.time.LocalDateTime;
import lombok.Data;

/* 以下のテーブルを参考にDTOクラスを作成する
emp_no     | int           
birth_date | date          
first_name | varchar(14)   
last_name  | varchar(16)   
gender     | enum('M','F') 
hire_date  | date          
salary     | int  Salaries.salaryのSumが入る想定
*/
@Data
public class EmployeeSalaryDTO {
  private int emp_no;
  // private LocalDateTime birth_date;
  private String first_name;
  private String last_name;
  // private String gender;
  // private LocalDateTime hire_date;
  // private int sumsalary;
}
