# Spring Batchのトランザクション制御サンプル

## 概要
例外が発生した際の挙動についてのサンプル
- 後続のStepが処理できること
- Step間で値の受け渡しができること
- 途中で例外が発生しても必ず実行するトランザクションというStepが用意できること

DB書き込みを行うのでH2を使う。

## JobContext
Step間で値の受け渡しをするためのクラス

## フロー制御
```java
return new JobBuilder("firstJob", jobRepository)
    .incrementer(new RunIdIncrementer())
    .listener(listener)
    .start(step1)
    .on("FAILED").to(step2)
    .next(step3)
    .from(step1).on("*").stop()
    .next(stepLog)
    .end()
    .build();
```
