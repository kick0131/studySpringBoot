package com.example.browsing_service.credit_impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.browsing_service.UserAccount;
import com.example.browsing_service.credit_interface.CreditApi;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RestController
@Slf4j
public class CreditApiViewImpl implements CreditApi {

  UserAccount user = null;
  WebDriver driver = null;
  // ユーザアカウント情報ファイルパス
  private final String SECURE_FILEPATH = "src/main/resources/static/secure_view.json";
  // 明細照会へのリンクID
  private final String DETAIL_INQUIRY = "vucGlobalNavi_LnkV0300_001Header";

  @RequestMapping("/view")
  public Map<Integer, String> getCreditInfo() {
    // seleniumSample();
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
    driver.findElement(By.id("id")).sendKeys(id);
    driver.findElement(By.id("pass")).sendKeys(pass);
    driver.findElement(By.name("loginForm")).submit();
  }

  @Override
  public Map<Integer,String> getMonthlyData() {
    // [top page]
    // 明細画面に遷移
    driver.findElement(By.id(DETAIL_INQUIRY)).click();

    // [detailmain page]
    // 格納先
    Map<Integer, String> map = new HashMap<>();
    // 開始月(今月)
    Calendar calendar = Calendar.getInstance();
    Integer year = calendar.get(Calendar.YEAR);
    Integer month = calendar.get(Calendar.MONTH) + 1;
    log.info(String.format("今月は%d月",month));

    // n月情報の取得
    // LnkClaimYmN 末尾Nは2-12までの数字
    // 2 : 今月
    // 3 : 1か月前
    // 12: 11か月前
    final String MONTH_ID_PREFIX = "LnkClaimYm";
    List<String> monthdatalist = new ArrayList<String>();
    for(int i=2; i<12; i++){
      monthdatalist.add(String.format("%s%d",MONTH_ID_PREFIX,i));
    }

    // 毎月のリンク取得(今月を起点に11か月前まで過去分)
    for (String monthdata : monthdatalist) {
      // 月別詳細画面に遷移
      String xpath_str = String.format("//a[@id='%s']", monthdata);
      driver.findElement(By.xpath(xpath_str)).click();;

      // [detail page]
      // 目的のテキストを取得し、Map型に格納
      // ページ遷移を繰り返して取得動作を行うが、取得が早すぎて失敗するので待機を挟む
      WebElement cost = new WebDriverWait(driver, Duration.ofSeconds(3))
        .until(
          ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='DivCustomerInfo']//strong")));
          // driver.findElement(By.xpath("//div[@id='DivCustomerInfo']//strong")));
      String cost_str = cost.getAttribute("innerHTML").strip();
      if (cost_str.isEmpty()) {
        // pass
      } else {
        log.info(cost_str);
        map.put(year*100+month, cost_str);
      }
      // 次月の設定(今月、先月、先々月...)
      month = month - 1;
      if (month == 0){
        month = 12;
        year -= 1; // 2022 -> 2021
      }
      // 前画面に戻る
      driver.findElement(By.id("BtnReSelect")).click();
    }

    // session end
    driver.quit();

    return map;
  }

}
