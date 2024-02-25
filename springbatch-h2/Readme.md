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

# 課題




