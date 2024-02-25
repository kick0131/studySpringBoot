# studySpringBoot

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/kick0131/studySpringBoot/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/kick0131/studySpringBoot/tree/main)

## description
SpringBoot関連サンプルプロジェクト集  
個々のプロジェクトの詳細についてはサブプロジェクト内のReadme.mdを参照

# Server side setup
## install JDK
```bash
# [Ubuntu]インストール可能なJDKの確認
sudo apt search "^openjdk.*jdk$"
# [Ubuntu]JDKのインストール
sudo apt install -y openjdk-XX-jdk
```

## install Maven
```bash
# [Ubuntu]Mavenのインストール
sudo apt install -y maven
```

# Client side setup

## install CircleCI CLI
```bash
# [Win]事前にChocoletyのインストールが必要
choco install circleci-cli -y
```

### CircleCI validation check
.circleci/config.ymlの文法チェック
```bash
circleci config validate
```

# VsCode
## install Extention
| Toolname | description |
| --- | --- |
| Spring Boot Extention Pack | 公式のVsCodeサポート。必須
| Extension Pack for Java | プロジェクトファイルを立ち上げようすると推奨される。必須 |
| Red Hat Dependency Analytics | ライブラリの脆弱性チェック |


上記入れればSpring Initializr Java SupportもSpring Boot Toolsも入る

## usage Extention
```bash
# コマンドパレットからSpringBoot initializr呼び出し
Ctrl + Shift + P
spring initializr

# 作りたい設定を選ぶ(省略)
# 初期設定で導入したいライブラリを選択
この辺りは大体必要
Spring Web
Spring Boot Tools
lombok
# プロジェクトの出力先を選ぶ
```

|type|extention name|description|
|--|--|--|
|I/O|Spring Batch    |Webアプリケーションではなくバッチアプリケーションの場合|
|I/O|Validation      ||
|I/O|Quarts Scheduler||

## Start SpringBoot Application for Maven
```bash
# build
mvn build
# run
mvn spring-boot:run
# resource clean
mvn clean
```
この時点でlocalhost:8080を叩いてもなにも用意していないので404エラーを返すのが正しい。


## Start SpringBoot Application for Gradle
```bash
# list of tasks
./gradlew tasks
# build
./gradlew build
# run
./gradlew bootRun
# resource clean
./gradlew clean
```

# トラブルシュート
## git bashが文字化けする
文字コードが正しいか確認
```bash
$ chcp.com
現在のコード ページ: 932
# UTF-8に変更
$ chcp.com 65001
Active code page: 65001
```
