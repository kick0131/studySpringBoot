package com.example.browsing_service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

@RestController
@Slf4j
public class DebugService {

  UserAccount user = null;
  WebDriver driver = null;
  private final String SECURE_FILEPATH="src/main/resources/static/secure.json";


  @RequestMapping("/debug/user")
  public String userinfo() {
    log.info("===== DEBUG Mode =====");
    try {
      user = new UserAccount(SECURE_FILEPATH);
    } catch (IOException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }

    return "Success userinfo";
  }

  @RequestMapping("/debug/selenium")
  public String debugselenium() {
    log.info("===== DEBUG Mode =====");
    seleniumSample();
    return "Success debug selenium";
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

}
