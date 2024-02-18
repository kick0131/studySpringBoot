# SpringBoot Scheduler

## Description
Schedulerアノテーションを使ったサンプル

- 1分単位に起動
- 5分でタイムアウト
- 10秒毎にログ出力を行う
  - logbackを使う

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

# 発展
## gradleで書き直す
- https://spring.pleiades.io/spring-boot/docs/current/reference/html/getting-started.html
- https://note.com/yucco72/n/n65119966d801

```bash
# build
mvn build
# run
mvn spring-boot:run
# resource clean
mvn clean
```
