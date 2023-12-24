# Azure MySQLアクセス
## 目次
1. [初期構築](#初期構築)
1. [ローカルのMySQLアクセス](#ローカルのmysqlアクセス)
1. [Azure上のMySQLアクセス](#azure上のmysqlアクセス)
1. [MyBatisを使う](#mybatisを使う)

## 環境
- VsCode
- SpringBoot3系
- MyBatis
- MySQL
- Azure CosmosDB

## 方針
- GUIは最小限、バックエンド中心の実装とする
- application.propertiesではなく、application.yamlを使う

## トラブルシュート
### com.mysql.cj.jdbc.Driverは非推奨
```bash
# application.propertiesに以下を書かない！！
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```


## アプリケーション共通操作
[Spring Boot Mavenプラグイン](https://spring.pleiades.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/)
```bash
# ライブラリインストール
mvn install
# キャッシュクリア
mvn clean
# 起動
mvn spring-boot:run
# jar作成
mvn spring-boot:repackage
# OCIイメージ作成
mvn spring-boot:build-image
```


# 初期構築
Helloworldまで

## application.propertiesをyamlに変更
application.yamlを作成

## MyBatisの設定を追加
初期構築時のTestで失敗する
```bash
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mysql
    username: devusr
    password: devpass
    driver-class-name: com.mysql.cj.jdbc.Driver
```

## HelloWorld
1. Controllerクラスを用意
2. Controllerクラスが返すindex.htmlを用意

これでlocalhost:8080/demoが動作。ここがスタート地点。


# ローカルのMySQLアクセス
1. MySQLのDBを用意
```bash
# id              integer       PK
# bodytext        varchar(255)
# create_datetime timestamp
```
1. application.yamlを作成したMySQLに合わせて修正
```bash
# 修正前
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mysql # ★修正
    username: devusr  # ★修正
    password: devpass # ★修正
    driver-class-name: com.mysql.cj.jdbc.Driver

# 修正後
spring:
  datasource:
    url: jdbc:mysql://localhost:9999/demodb?enabledTLSProtocols=TLSv1.2
    username: demouser
    password: demopass
    driver-class-name: com.mysql.cj.jdbc.Driver
```

# Azure上のMySQLアクセス


# MyBatisを使う

# トラブルシュート
## CommunicationsException: Communications link failure
### 問題
MySQLの接続に失敗する

### 原因と対策
1. MySQLの接続に失敗している
    - application.properties(yaml)の接続情報を見直す
    ```bash
    url: jdbc:mysql://localhost:9999/demodb?
                      1~~~~~~~~~~~~~ 2~~~~~
    ```
    - サーバ上でPodmanコンテナを起動している場合、ポート転送して外部から接続を許可する必要がある
    - SSH接続している場合、TeratermなどのSSH転送と組み合わせる必要がある
    - MySQLクライアントの代わりにHeidiSQLを使って接続できるか確認する
    - 2の部分はDB名を指定する。接続先DBが間違っていないか。

1. TLSバージョン1.0が使用できない  
    enabledTLSProtocols=TLSv1.2オプションを付与する
    ```yaml
    jdbc:mysql://localhost:3306/testdb?enabledTLSProtocols=TLSv1.2
    ```

