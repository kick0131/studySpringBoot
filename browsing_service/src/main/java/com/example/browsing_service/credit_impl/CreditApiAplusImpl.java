package com.example.browsing_service.credit_impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.browsing_service.UserAccount;
import com.example.browsing_service.credit_interface.CreditApi;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@RestController
@Slf4j
public class CreditApiAplusImpl implements CreditApi {

  UserAccount user = null;
  WebDriver driver = null;
  // ユーザアカウント情報ファイルパス
  private final String SECURE_FILEPATH = "src/main/resources/static/secure_aplus.json";

  @RequestMapping("/aplus")
  public Map<Integer, String> getCreditInfo() {
    try {
      user = new UserAccount(SECURE_FILEPATH);
      login(user.model.url, user.model.id, user.model.password);
      return getMonthlyData();

    } catch (IOException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public void login(String loginurl, String id, String pass) {

    driver = new ChromeDriver();
    driver.get(loginurl);

    // [login page]
    // ID、パスワードを入力し、ログインボタン押下(submit)
    driver.findElement(By.id("webMemberId")).sendKeys(id);
    driver.findElement(By.id("webMemberPass")).sendKeys(pass);
    driver.findElement(By.xpath("//input[@value='ログイン']")).click();
  }

  @Override
  public Map<Integer, String> getMonthlyData() {
    // [top page]
    // 格納先
    Map<Integer, String> map = new HashMap<>();
    // 4月開始固定
    Calendar calendar = Calendar.getInstance();
    Integer year = calendar.get(Calendar.YEAR);
    Integer month = 4;

    // 月別情報の取得(4月開始)
    List<WebElement> costs = driver.findElements(By.xpath("//div[@class='dl-tbl-box-inner mb-15']/dl/dd"));
    log.info("costs.size:{}", costs.size());
    for (WebElement cost : costs) {
      String cost_str = cost.getAttribute("innerHTML").strip();
      if (cost_str.isEmpty()) {
        // pass
      } else {
        log.info(cost_str);
        map.put(year * 100 + month, cost_str);
      }
      // 翌月の設定(4,5,6...12,1,2,3)
      month = month + 1;
      if (month == 13) {
        month = 1;
      }
    }

    // session end
    driver.quit();

    return map;
  }

}
