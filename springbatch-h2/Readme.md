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

application.yamlで実行するジョブを指定する(spring.batch.job.name)  
説明の便宜上この使い方をしているが、実際の開発は「1バッチ1プロジェクト」の方が使い勝手が良いと思う。
```yaml
spring:
  batch:
    job:
      enabled: true
      name: secondJob
```

## 定期実行
```ToDo:定期実行されずに終了する。要調査```
1. Scheduleを有効にするため、mainのクラスにEnableSchedulingアノテーションを付与する
    ```java
    @Configuration
    @EnableScheduling
    ```
1. 
### (検討)サービス側で定期実行した方が良い
- Scheduleを使えるかを調査
- その上でScheduleではなく外から定期実行させた方が良い理由を記載


# HowTo
## Configurationアノテーション
Configurationアノテーションを付与したクラスは内部でBeanアノテーションを使うことができる。  
Beanアノテーションを付与し、DIコンテナにBeanを登録することができる。

## StepScopeアノテーション
SpringBatch専用のアノテーション。  
JobScopeとStepScopeが存在し、ジョブ実施毎、ステップ実施毎にインスタンスの生成を行う。  
Scopeアノテーションの一種。

## NonNullアノテーション(org.springframework.lang.NonNull)
このアノテーションがついた引数にnullを渡そうとするとNullPointerException例外を発生する。



## Chunk
- ItemReader < Output >
  - readインタフェースを持ち、ProcessorまたはWriterに渡される
  - ジェネリクス(型指定)をする方がバグを生みにくい
  - nullを返却すると処理が終了し、後続のProcessor、Writerは処理されない
- ItemProcessor < Input, Output>
  - InputでReaderから受け取り、OutputでWriterに渡す
  - nullを返却すると処理が終了し、後続のWriterは処理されない
- ItemWriter < Input >
  - writeインタフェースを持ち、ReaderまたはProcessorから受けとったInputを扱う
- chunk()で指定した回数Reader,Processorを繰り返し実行後、Writerを実行する

## Listener
実際はXXXExecutionListenerというクラスを継承したクラスを作成する。
|Listener|説明|
|--|--|
|JobListener          |Job実行前後|
|StepListener         |Step実行前後|
|ChunkListener        |Chunk実行前後|
|ItemReadListener     |Reader実行前後|
|ItemProcessorListener|Processor実行前後|
|ItemWriterListener   |Writer実行前後|
|RetryListener        |Retry実行前後|
|SkipListener         |Chunkでエラーが発生した時|

## 値の受け渡し
SpringBoot解体新書(バッチ編)
```ToDo```


## バッチ実行方法
SpringBoot解体新書(バッチ編)
```ToDo```

## MyBatis実行方法
SpringBoot解体新書(バッチ編)
```ToDo```

## エラー処理
SpringBoot解体新書(バッチ編)
```ToDo```
- ロールバックしつつDB書き込みする方法

## テスト(JUnit)
SpringBoot解体新書(バッチ編)
```ToDo```



# トラブルシュート
## H2を使うとDBに接続できないエラーが発生する
application.yaml参照。  
org.springframework.boot.devtoolsを使っていると発生するので除外する。

## 複数Job定義ができない
Spring Boot3(Spring Batch5)から仕様になった  
- [tack overflow](https://stackoverflow.com/questions/76253416/using-spring-batch-job-names-with-spring-boot-3-and-spring-batch-5)
- application.yamlのspring.batch.job.nameで実行ジョブを定義する
- 実行ジョブを1つのみにする




