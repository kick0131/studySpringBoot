package com.example.postgresoncloudsql;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CloudSqlClient {
  @RequestMapping("/")
  public String hello() {
    log.info("hello world");
    return "helloworld";
  }

  @RequestMapping("/login")
  public String login() {
    try {
      // TODO:ログイン処理の実装
      log.info("ToDo");
    } catch (Exception e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }

    return "SUCCESS";
  }
}