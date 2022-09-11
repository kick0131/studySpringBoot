package com.example.browsing_service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class CreditApiAplusImpl implements CreditApi{

  @RequestMapping("/") 
  public String hello() {
    login("iidd", "passpass");
    return "hello world";
  }

  @Override
  public String login(String id, String pass) {
    log.info("== start [param: {} {}]", id, pass);
    return null;
  }

  @Override
  public String getMonthlyData(String yyyymm) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
