package com.example.mysqlonazure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// 初期構築が正しくできているかの動作確認用
// HTTPリクエストを受け取って所定のhtmlファイルを応答する
@Controller
public class demoController {
  @RequestMapping(value="/demo", method=RequestMethod.GET)
  private String hello() {
      return "/index.html";
  }  
}
