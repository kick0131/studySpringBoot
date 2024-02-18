# SpringBoot Scheduler

## Description
Schedulerアノテーションを使ったサンプル

- 5秒毎起動し、ログ出力を行う
  - logbackを使う
- 11秒でタイムアウト

# 実装
1. [logbackを使ったログ書き込み](#logbackを使ったログ書き込み)
1. [Schedulerアノテーションの実装](#schedulerアノテーションの実装)
1. [タイムアウトの実装](#タイムアウトの実装)

## logbackを使ったログ書き込み
- logback-spring.xmlを配置する
- application.yamlを配置する

## Scheduledアノテーションの実装
- 呼び出し側にConfiguration,EnableAsync,EnableSchedulingアノテーションを付与する
  ```java
  @Configuration
  @EnableAsync
  @EnableScheduling
  @SpringBootApplication
  public class SpringbootSchedulerApplication {
  ```
- 呼び出され側にScheduledアノテーションを付与する
  ```java
  @Scheduled(cron = "${job.start.cron}")
  public void reportCurrentTime() {
    System.out.println("Current Time: " + dateFormat.format(new Date()));
  }
  ```
- ユーザ定義のプロパティを読み込むようにapplication.yamlを修正
  ```yaml
  config:
    import: classpath:/system-properties.yaml
  ```
- ユーザ定義のプロパティ(system-properties.yaml)にcron定義を記載
  ```yaml
  job:
    start:
      cron: "*/5 * * * * *"  
  ```

## タイムアウトの実装
- 起動と同じ方法でタイムアウトを実装可能
  ```java
  @Scheduled(cron = "${job.check.timeout.cron}")
  public void exitBatch() {
  ```


# 発展
## gradleで書き直す
- https://spring.pleiades.io/spring-boot/docs/current/reference/html/getting-started.html
- https://note.com/yucco72/n/n65119966d801


## アプリ実行
```bash
# build
mvn build
# run
mvn spring-boot:run
# resource clean
mvn clean
```

# 課題
## 終了方法
Webアプリで定期実行させている場合、アプリ終了はできない。




