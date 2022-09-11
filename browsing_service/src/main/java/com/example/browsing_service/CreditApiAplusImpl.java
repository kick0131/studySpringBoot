package com.example.browsing_service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

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

  @RequestMapping("/")
  public String getCreditInfo() {
    // TODO: パスパラメータで必要な情報を絞り込みできるように
    // seleniumSample();
    try {
      user = new UserAccount();
      login(user.model.url, user.model.id, user.model.password);
    } catch (IOException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
 
    return "hello world";
  }

  @RequestMapping("/user")
  public String userinfo() {
    log.info("=====");
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
  public String login(String loginurl, String id, String pass) {

    WebDriver driver = new ChromeDriver();
    driver.get(loginurl);

    // [login page]
    // ID、パスワードを入力し、ログインボタン押下(submit)
    driver.findElement(By.id("webMemberId")).sendKeys(id);
    driver.findElement(By.id("webMemberPass")).sendKeys(pass);
    driver.findElement(By.xpath("//input[@value='ログイン']")).click();

    // [top page]
    // get parameter
    // WebElement target = driver.findElement(By.xpath("//div[@id='change02']/h3")); // OK
    WebElement target = driver.findElement(By.xpath("//div[@id='change02']//dd[1]")); // NG
    System.out.println(target.getText());

    // ng
    // List<WebElement> costs = driver.findElements(By.xpath("//div[@class='dl-tbl-box-inner mb-15']/dl/dd"));
    // log.info("costs.size:{}", costs.size());
    // for (WebElement cost : costs) {
    //   System.out.println(cost.getText());
    // }
    
    // session end
    driver.quit();
    return null;
  }

  @Override
  public String getMonthlyData(String yyyymm) {
    // TODO Auto-generated method stub
    return null;
  }

}
