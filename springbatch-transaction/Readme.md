# Spring Batchのトランザクション制御サンプル

## 概要
例外が発生した際の挙動についてのサンプル
- 後続のStepが処理できること
- Step間で値の受け渡しができること
- 途中で例外が発生しても必ず実行するトランザクションというStepが用意できること

DB書き込みを行うのでH2を使う。

### ソースについて
SpringBatch-h2からソースを流用している。  
フロー制御のサンプルプロジェクトとして別プロジェクト化している。  
基本的な説明はSpringBatch-h2を参照すること。


### ジョブの説明
- firstJob
    - フロー制御の練習ジョブ
    - Tasklet1で意図的に例外を発生させてフロー分岐を確認する
    - 例外が発生するとそのトランザクションはロールバックするが、ロールバックしても残したいデータを  
    JobExecutionContextに格納し、他のステップで使えるようにする
        - Tasklet1 : DB書き込み、JobExecution書き込み
        - Tasklet2 : DB書き込み、JobExecution書き込み
        - Tasklet3 : DB読み込み
        - LogTasklet : JobExecution読み込み
    
- secondJob
    - 特に意味はない

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
