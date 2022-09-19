package com.example.browsing_service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

@RestController
@Slf4j
public class CreditApiAplusImpl implements CreditApi {

  UserAccount user = null;
  WebDriver driver = null;

  @RequestMapping("/")
  public Map<Integer, String> getCreditInfo() {
    // TODO: パスパラメータで必要な情報を絞り込みできるように
    // seleniumSample();
    try {
      user = new UserAccount();
      login(user.model.url, user.model.id, user.model.password);
      return getMonthlyData();
      
    } catch (IOException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }

    return null;
  }

  @RequestMapping("/debug/user")
  public String userinfo() {
    log.info("===== DEBUG Mode =====");
    try {
      user = new UserAccount();
    } catch (IOException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }

    return "Success userinfo";
  }

  public void seleniumSample() {
    /// Seleniumの動作サンプル
    ///
    WebDriver driver = new ChromeDriver();

    final String URL = "http://www.google.com";
    driver.get(URL);

    // 唯一の<input>要素を取得
    WebElement element = driver.findElement(By.name("q"));
    element.sendKeys("selenium");
    element.submit();

    new WebDriverWait(driver, Duration.ofSeconds(10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        return d.getTitle().toLowerCase().startsWith("selenium");
      }
    });

    System.out.println("Page title is: " + driver.getTitle());
    driver.quit();
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
  public Map<Integer,String> getMonthlyData() {
    // [top page]
    // 格納先
    Map<Integer, String> map = new HashMap<>();
    // 4月開始
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
        map.put(month, cost_str);
      }
      // 翌月の設定(4,5,6...12,1,2,3)
      month = month + 1;
      if (month == 13){
        month = 1;
      }
    }

    // session end
    driver.quit();

    return map;
  }

}
