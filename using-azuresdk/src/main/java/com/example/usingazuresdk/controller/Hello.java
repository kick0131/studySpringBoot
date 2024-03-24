package com.example.usingazuresdk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

// index.htmlを表示する
@Controller
@Slf4j
public class Hello {

  @GetMapping("/")
  public String getIndex() {
    log.info("サンプルメソッド呼び出し : Hello");
    return "index2.html";
  }

}
