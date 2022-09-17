package com.example.postgresoncloudsql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.KenkoRecord;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CloudSqlClient {

  @Autowired
  private JdbcService service;

  @RequestMapping("/")
  public String hello() {
    log.info("hello world");
    return "helloworld";
  }

  @RequestMapping("/list")
  public List<KenkoRecord> list() {
    try {
      log.info("ToDo");
      return service.findAll();
    } catch (Exception e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
    return null;
  }
}