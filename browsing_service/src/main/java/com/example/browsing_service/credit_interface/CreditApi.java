package com.example.browsing_service.credit_interface;

import java.util.Map;

public interface CreditApi {
  // ログイン動作を実装する
  void login(String loginurl, String id, String pass);

  // 月別の情報をMap型に格納し、返却する実装
  // {
  //   "202201":"1月の情報",
  //   "202202":"2月の情報",
  //   ...
  //   "202212":"12月の情報",
  // }
  // TODO: 月情報しか分からないので去年か今年かの判断ができた方が良い
  //
  Map<Integer,String> getMonthlyData();
}
