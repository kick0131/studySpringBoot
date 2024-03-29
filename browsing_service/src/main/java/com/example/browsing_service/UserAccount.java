package com.example.browsing_service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserAccount {
  public UserAccountModel model = null;

  public UserAccount(String securefilepath) throws IOException {
    // JSONファイルからセキュア情報を取得
    File file = new File(securefilepath);

    try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      // JSONファイルの文字列化
      String data;
      StringBuffer json = new StringBuffer();
      while ((data = bufferedReader.readLine()) != null) {
        json.append(data);
      }
      // ObjectMapperを生成
      ObjectMapper mapper = new ObjectMapper();
      // Jsonを読み込んで、modelクラスにマッピングする
      model = mapper.readValue(json.toString(), UserAccountModel.class);
    }
  }
}
