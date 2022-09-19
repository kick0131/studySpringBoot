package com.example.browsing_service;

import java.util.Map;

interface CreditApi {
  public String loginsession = "";
  void login(String loginurl, String id, String pass);
  Map<Integer,String> getMonthlyData();
}
