# SpringBatch and H2 Database

## Description
SpringBatchサンプルコード

- シンプルなHelloWorldバッチ実行を行う
- DBのCRUD操作をバッチで実現する
  - 1秒ごとに以下の処理を行う
    - Create
    - Read
    - Update
    - Read
    - Delete

## 前提
- gradleがインストール済みであること
```bash
# 最低条件
$ gradle -v
------------
Gradle 7.6.4
------------

# SpringBootプロジェクトであればgradlewを使った方が良い
$ ./gradlew -v
---
Gradle 8.5
---
```

## Spring Initializr
|Extention|
|--|
|Spring Boot DevTools|
|Lombok|
|H2 Database|
|Spring Batch|


# 実装
1. [HelloWorldサンプル](#HelloWorldサンプル)
1. [DB操作](#DB操作)

## HelloWorldサンプル
- logback-spring.xmlを配置する
- application.yamlを配置する

## DB操作

```yaml
# H2 Database接続設定
spring:
  datasource:
    url: "jdbc:h2:mem:test"
    username: sa
    password:
    driver-class-name: org.h2.Driver
```



## アプリ実行
```bash
# アプリケーションを直接実行
./gradlew bootRun

# 実行可能jarを作成し、別途コマンドラインから実行
# build/libs配下にjarが生成される
./gradlew bootJar
java -jar build/libs/xxxx.jar
```

# トラブルシュート
## H2を使うとDBに接続できないエラーが発生する
application.yaml参照。  
org.springframework.boot.devtoolsを使っていると発生するので除外する。

## 複数Job定義ができない
Spring Boot3(Spring Batch5)から仕様になった  
- [tack overflow](https://stackoverflow.com/questions/76253416/using-spring-batch-job-names-with-spring-boot-3-and-spring-batch-5)
- application.yamlのspring.batch.job.nameで実行ジョブを定義する
- 実行ジョブを1つのみにする





