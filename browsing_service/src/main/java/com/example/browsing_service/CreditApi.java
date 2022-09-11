package com.example.browsing_service;

interface CreditApi {
  public String loginsession = "";
  String login(String id, String pass);
  String getMonthlyData(String yyyymm);
}
