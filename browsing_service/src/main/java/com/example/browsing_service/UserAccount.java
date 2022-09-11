package com.example.browsing_service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAccount {
  public UserAccountModel model = null;

  UserAccount() throws IOException {
    // JSONファイルからセキュア情報を取得
    File file = new File("src/main/resources/static/secure.json");

    try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      // JSONファイルの文字列化
      String data;
      StringBuffer json = new StringBuffer();
      while ((data = bufferedReader.readLine()) != null) {
        System.out.println(data);
        json.append(data);
      }
      // ObjectMapperを生成
      ObjectMapper mapper = new ObjectMapper();
      // Jsonを読み込んで、modelクラスにマッピングする
      model = mapper.readValue(json.toString(), UserAccountModel.class);
    }
  }
}
